package com.services.api.controller;

import com.services.api.dto.ApiMessageDto;
import com.services.api.dto.ResponseListObj;
import com.services.api.dto.account.VerifyDto;
import com.services.api.dto.customer.CustomerAdminDto;
import com.services.api.dto.customer.CustomerClientDto;
import com.services.api.exception.UnauthorizationException;
import com.services.api.form.customer.CreateCustomerForm;
import com.services.api.form.customer.UpdateCustomerForm;
import com.services.api.mapper.CustomerMapper;
import com.services.api.service.EmailService;
import com.services.api.service.QRCodeOTPService;
import com.services.api.storage.criteria.CustomerCriteria;
import com.services.api.storage.model.Account;
import com.services.api.storage.model.Customer;
import com.services.api.storage.repository.AccountRepository;
import com.services.api.storage.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/customer")
@Slf4j
@CrossOrigin(allowedHeaders = "*", origins = "*")
public class CustomerController extends ABasicController{
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CustomerMapper customerMapper;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private QRCodeOTPService qrCodeOTPService;
    @Autowired
    private EmailService emailService;
    PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiMessageDto<ResponseListObj<CustomerAdminDto>> list(CustomerCriteria customerCriteria, Pageable pageable){
        if(!isAdmin()){
            throw new UnauthorizationException("Not allowed to list");
        }
        ApiMessageDto<ResponseListObj<CustomerAdminDto>> apiMessageDto = new ApiMessageDto<>();
        Page<Customer> customerPage = customerRepository.findAll(customerCriteria.getSpecification(), pageable);
        ResponseListObj<CustomerAdminDto> responseListObj = new ResponseListObj<>();
        responseListObj.setData(customerMapper.fromAdminEntityListToDtoList(customerPage.getContent()));
        responseListObj.setPage(pageable.getPageNumber());
        responseListObj.setTotalPage(customerPage.getTotalPages());

        apiMessageDto.setData(responseListObj);
        apiMessageDto.setMessage("List customer success");
        return apiMessageDto;
    }

    @GetMapping(value = "/get/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiMessageDto<CustomerAdminDto> get(@PathVariable Long id){
        if(!isAdmin()){
            throw new UnauthorizationException("Not allowed to get");
        }
        ApiMessageDto<CustomerAdminDto> apiMessageDto = new ApiMessageDto<>();
        Customer customer = customerRepository.findById(id).orElse(null);
        CustomerAdminDto customerAdminDto = customerMapper.fromAdminEntityToDto(customer);
        apiMessageDto.setData(customerAdminDto);
        apiMessageDto.setMessage("Get customer success");
        return apiMessageDto;
    }

    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiMessageDto<String> create(@Valid @RequestBody CreateCustomerForm createCustomerForm, BindingResult bindingResult){
        if(!isAdmin()){
            throw new UnauthorizationException("Not allowed to create");
        }
        ApiMessageDto<String> apiMessageDto = new ApiMessageDto<>();
        Customer customer = customerMapper.fromCreateFormToEntity(createCustomerForm);
        Account account = customerMapper.fromCreateFormToAccount(createCustomerForm);
        account.setKind(2);
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        customer.setAccount(account);
        customerRepository.save(customer);
        apiMessageDto.setMessage("Create customer success");
        return apiMessageDto;
    }

    @PutMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiMessageDto<String> update(@Valid @RequestBody UpdateCustomerForm updateCustomerForm, BindingResult bindingResult){
        if(!isAdmin()){
            throw new UnauthorizationException("Not allowed to update");
        }
        ApiMessageDto<String> apiMessageDto = new ApiMessageDto<>();
        Customer customer = customerRepository.findById(updateCustomerForm.getId()).orElse(null);
        if(customer == null) {
            apiMessageDto.setResult(false);
            apiMessageDto.setMessage("customer's id doesn't exist");
        }
        customerMapper.fromUpdateFormToEntity(updateCustomerForm, customer);
        Account account = accountRepository.findById(updateCustomerForm.getId()).orElse(null);
        customerMapper.fromUpdateFormToAccount(updateCustomerForm, account);
        accountRepository.save(account);
        customer.setAccount(account);
        customerRepository.save(customer);
        apiMessageDto.setMessage("Update customer success");
        return apiMessageDto;
    }

    @Transactional
    @DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiMessageDto<String> delete(@PathVariable Long id){
        if(!isAdmin()){
            throw new UnauthorizationException("Not allowed to delete");
        }
        ApiMessageDto<String> apiMessageDto = new ApiMessageDto<>();
        Customer customer = customerRepository.findById(id).orElse(null);
        if(customer == null) {
            apiMessageDto.setResult(false);
            apiMessageDto.setMessage("customer doesn't exist to delete");
            return apiMessageDto;
        }
        customerRepository.deleteById(id);
        accountRepository.deleteById(id);
        apiMessageDto.setMessage("Delete customer success");
        return apiMessageDto;
    }

    @GetMapping(value = "/get-profile", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiMessageDto<CustomerClientDto> getProfile(){
        ApiMessageDto<CustomerClientDto> apiMessageDto = new ApiMessageDto<>();
        Long id = getCurrentUserId();
        Customer customer = customerRepository.findById(id).orElse(null);
        CustomerClientDto customerClientDto = customerMapper.fromClientEntityToDto(customer);
        apiMessageDto.setData(customerClientDto);
        apiMessageDto.setMessage("Get customer success");
        return apiMessageDto;
    }

    @PostMapping(value = "/register", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiMessageDto<VerifyDto> register(@Valid @RequestBody CreateCustomerForm createCustomerForm, BindingResult bindingResult){
        ApiMessageDto<VerifyDto> apiMessageDto = new ApiMessageDto<>();
        Customer customer = customerMapper.fromCreateFormToEntity(createCustomerForm);
        Account account = customerMapper.fromCreateFormToAccount(createCustomerForm);
        account.setKind(2);
        account.setStatus(0);
        account.setVerifyTime(0);
        String otp = qrCodeOTPService.generate(6);
        account.setOtp(otp);
        customer.setStatus(0);
        customer.setAccount(account);
        customerRepository.save(customer);
        emailService.sendEmail(createCustomerForm.getEmail(), otp, "verify code", true);
        VerifyDto verifyDto = new VerifyDto();
        verifyDto.setId(customer.getId());
        verifyDto.setUsername(createCustomerForm.getUsername());
        verifyDto.setEmail(createCustomerForm.getEmail());
        apiMessageDto.setData(verifyDto);
        apiMessageDto.setMessage("Create customer success");
        return apiMessageDto;
    }

    @PutMapping(value = "/update-profile", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiMessageDto<String> updateProfile(@Valid @RequestBody UpdateCustomerForm updateCustomerForm, BindingResult bindingResult){
        ApiMessageDto<String> apiMessageDto = new ApiMessageDto<>();
        Customer customer = customerRepository.findById(updateCustomerForm.getId()).orElse(null);
        if(customer == null) {
            apiMessageDto.setResult(false);
            apiMessageDto.setMessage("customer's id doesn't exist");
        }
        customerMapper.fromUpdateFormToEntity(updateCustomerForm, customer);
        Account account = accountRepository.findById(updateCustomerForm.getId()).orElse(null);
        customerMapper.fromUpdateFormToAccount(updateCustomerForm, account);
        accountRepository.save(account);
        customer.setAccount(account);
        customerRepository.save(customer);
        apiMessageDto.setMessage("Update customer success");
        return apiMessageDto;
    }
}

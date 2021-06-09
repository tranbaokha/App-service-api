package com.services.api.controller;

import com.services.api.constant.ServiceConstant;
import com.services.api.dto.ApiMessageDto;
import com.services.api.dto.ResponseListObj;
import com.services.api.dto.account.VerifyDto;
import com.services.api.dto.agency.AgencyAdminDto;
import com.services.api.dto.agency.AgencyClientDto;
import com.services.api.dto.agency.FindServiceDto;
import com.services.api.exception.UnauthorizationException;
import com.services.api.form.agency.*;
import com.services.api.mapper.AgencyMapper;
import com.services.api.mapper.ServiceMapper;
import com.services.api.service.EmailService;
import com.services.api.service.QRCodeOTPService;
import com.services.api.storage.criteria.AgencyCriteria;
import com.services.api.storage.model.*;
import com.services.api.storage.repository.*;
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
import java.util.List;
import java.util.Objects;

@RestController
@Slf4j
@RequestMapping("/v1/agency")
@CrossOrigin(allowedHeaders = "*", origins = "*")
public class AgencyController extends ABasicController{
    @Autowired
    private AgencyRepository agencyRepository;
    @Autowired
    private AgencyMapper agencyMapper;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private QRCodeOTPService qrCodeOTPService;
    @Autowired
    private EmailService emailService;
    @Autowired
    private AgencyServiceRepository agencyServiceRepository;
    @Autowired
    private PositionRepository positionRepository;
    @Autowired
    private ServiceRepository serviceRepository;
    @Autowired
    private ServiceMapper serviceMapper;
    PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiMessageDto<ResponseListObj<AgencyAdminDto>> list(AgencyCriteria agencyCriteria, Pageable pageable){
        if(!isAdmin()){
            throw new UnauthorizationException("Not allowed to list");
        }
        ApiMessageDto<ResponseListObj<AgencyAdminDto>> apiMessageDto = new ApiMessageDto<>();
        Page<Agency> agencyPage = agencyRepository.findAll(agencyCriteria.getSpecification(), pageable);
        ResponseListObj<AgencyAdminDto> responseListObj = new ResponseListObj<>();
        responseListObj.setData(agencyMapper.fromAdminEntityListToDtoList(agencyPage.getContent()));
        responseListObj.setPage(pageable.getPageNumber());
        responseListObj.setTotalPage(agencyPage.getTotalPages());

        apiMessageDto.setData(responseListObj);
        apiMessageDto.setMessage("List agency success");
        return apiMessageDto;
    }

    @GetMapping(value = "/get/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiMessageDto<AgencyAdminDto> get(@PathVariable Long id){
        if(!isAdmin()){
            throw new UnauthorizationException("Not allowed to get");
        }
        ApiMessageDto<AgencyAdminDto> apiMessageDto = new ApiMessageDto<>();
        Agency agency = agencyRepository.findById(id).orElse(null);
        AgencyAdminDto agencyAdminDto = agencyMapper.fromEntityAdminToDto(agency);
        apiMessageDto.setData(agencyAdminDto);
        apiMessageDto.setMessage("Get agency success");
        return apiMessageDto;
    }

    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiMessageDto<String> create(@Valid @RequestBody CreateAgencyForm createAgencyForm, BindingResult bindingResult){
        if(!isAdmin()){
            throw new UnauthorizationException("Not allowed to create");
        }
        ApiMessageDto<String> apiMessageDto = new ApiMessageDto<>();
        Agency agency = agencyMapper.fromCreateFormToEntity(createAgencyForm);
        Account account = agencyMapper.fromCreateFormToAccount(createAgencyForm);
        account.setKind(3);
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        agency.setAccount(account);
        agencyRepository.save(agency);
        apiMessageDto.setMessage("Create agency success");
        return apiMessageDto;
    }

    @PutMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiMessageDto<String> update(@Valid @RequestBody UpdateAgencyForm updateAgencyForm, BindingResult bindingResult){
        if(!isAdmin()){
            throw new UnauthorizationException("Not allowed to update");
        }
        ApiMessageDto<String> apiMessageDto = new ApiMessageDto<>();
        Agency agency = agencyRepository.findById(updateAgencyForm.getId()).orElse(null);
        if(agency == null) {
            apiMessageDto.setResult(false);
            apiMessageDto.setMessage("agency's id doesn't exist");
        }
        agencyMapper.fromUpdateFormToEntity(updateAgencyForm, agency);
        Account account = accountRepository.findById(updateAgencyForm.getId()).orElse(null);
        agencyMapper.fromUpdateFormToAccount(updateAgencyForm, account);
        accountRepository.save(account);
        agency.setAccount(account);
        agencyRepository.save(agency);
        apiMessageDto.setMessage("Update agency success");
        return apiMessageDto;
    }

    @Transactional
    @DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiMessageDto<String> delete(@PathVariable Long id){
        if(!isAdmin()){
            throw new UnauthorizationException("Not allowed to delete");
        }
        ApiMessageDto<String> apiMessageDto = new ApiMessageDto<>();
        Agency agency = agencyRepository.findById(id).orElse(null);
        if(agency == null) {
            apiMessageDto.setResult(false);
            apiMessageDto.setMessage("agency doesn't exist to delete");
            return apiMessageDto;
        }
        agencyRepository.deleteById(id);
        accountRepository.deleteById(id);
        apiMessageDto.setMessage("Delete agency success");
        return apiMessageDto;
    }

    @GetMapping(value = "/get-profile", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiMessageDto<AgencyClientDto> getProfile(){
        ApiMessageDto<AgencyClientDto> apiMessageDto = new ApiMessageDto<>();
        Long id = getCurrentUserId();
        Agency agency = agencyRepository.findById(id).orElse(null);
        AgencyClientDto agencyClientDto = agencyMapper.fromEntityClientToDto(agency);
        apiMessageDto.setData(agencyClientDto);
        apiMessageDto.setMessage("Get agency success");
        return apiMessageDto;
    }

    @PostMapping(value = "/register", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiMessageDto<VerifyDto> register(@Valid @RequestBody CreateAgencyForm createAgencyForm, BindingResult bindingResult){
        ApiMessageDto<VerifyDto> apiMessageDto = new ApiMessageDto<>();
        Agency agency = agencyMapper.fromCreateFormToEntity(createAgencyForm);
        Account account = agencyMapper.fromCreateFormToAccount(createAgencyForm);
        account.setKind(3);
        account.setStatus(0);
        account.setVerifyTime(0);
        String otp = qrCodeOTPService.generate(6);
        account.setOtp(otp);
        agency.setStatus(0);
        agency.setAccount(account);
        agencyRepository.save(agency);
        emailService.sendEmail(createAgencyForm.getEmail(), otp, "verify code", true);
        VerifyDto verifyDto = new VerifyDto();
        verifyDto.setId(agency.getId());
        verifyDto.setUsername(createAgencyForm.getUsername());
        verifyDto.setEmail(createAgencyForm.getEmail());
        apiMessageDto.setData(verifyDto);
        apiMessageDto.setMessage("Create agency success");
        return apiMessageDto;
    }

    @PutMapping(value = "/update-profile", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiMessageDto<String> updateProfile(@Valid @RequestBody UpdateAgencyForm updateAgencyForm, BindingResult bindingResult){
        ApiMessageDto<String> apiMessageDto = new ApiMessageDto<>();
        Agency agency = agencyRepository.findById(updateAgencyForm.getId()).orElse(null);
        if(agency == null) {
            apiMessageDto.setResult(false);
            apiMessageDto.setMessage("agency's id doesn't exist");
        }
        agencyMapper.fromUpdateFormToEntity(updateAgencyForm, agency);
        Account account = accountRepository.findById(updateAgencyForm.getId()).orElse(null);
        agencyMapper.fromUpdateFormToAccount(updateAgencyForm, account);
        accountRepository.save(account);
        agency.setAccount(account);
        agencyRepository.save(agency);
        apiMessageDto.setMessage("Update agency success");
        return apiMessageDto;
    }

    @PostMapping(value = "/add-service", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiMessageDto<String> addService(@RequestBody @Valid AddServiceForm addServiceForm, BindingResult bindingResult) {
        for(Long serviceId : addServiceForm.getServiceIdList()){
            AgencyService agencyService = new AgencyService();
            agencyService.setAgencyId(addServiceForm.getAgencyId());
            agencyService.setServiceId(serviceId);
            agencyService.setStatus(0);
            agencyServiceRepository.save(agencyService);
        }
        ApiMessageDto<String> apiMessageDto = new ApiMessageDto<>();
        apiMessageDto.setMessage("Add service success");
        return apiMessageDto;
    }

    @PutMapping(value = "/update-service", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiMessageDto<String> updateService(@RequestBody @Valid UpdateServiceForm updateServiceForm, BindingResult bindingResult) {
        AgencyService agencyService = agencyServiceRepository.findById(updateServiceForm.getId()).orElse(null);
        agencyService.setServiceId(updateServiceForm.getNewServiceId());
        agencyServiceRepository.save(agencyService);
        ApiMessageDto<String> apiMessageDto = new ApiMessageDto<>();
        apiMessageDto.setMessage("Update service success");
        return apiMessageDto;
    }

    @PostMapping(value = "/active-service", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiMessageDto<String> activeService(@RequestBody @Valid ActiveServiceForm activeServiceForm, BindingResult bindingResult){
        ApiMessageDto<String> apiMessageDto = new ApiMessageDto<>();
        if(!isAdmin()){
            apiMessageDto.setResult(false);
            apiMessageDto.setMessage("Not allowed to active");
            return apiMessageDto;
        }
        AgencyService agencyService = agencyServiceRepository.findById(activeServiceForm.getId()).orElse(null);
        agencyService.setStatus(1);
        agencyServiceRepository.save(agencyService);
        apiMessageDto.setMessage("active service success");
        return apiMessageDto;
    }

    @PostMapping(value = "/update-position", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiMessageDto<String> updatePosition(@RequestBody @Valid UpdatePositionForm updatePositionForm, BindingResult bindingResult) {
        List<Position> positionList = positionRepository.findByAgencyId(getCurrentUserId());
        for(Position position : positionList) {
            position.setLatitude(updatePositionForm.getLatitude());
            position.setLongitude(updatePositionForm.getLongitude());
            positionRepository.save(position);
        }
        ApiMessageDto<String> apiMessageDto = new ApiMessageDto<>();
        apiMessageDto.setMessage("update position success");
        return apiMessageDto;
    }

    @PostMapping(value = "/find-nearest-service", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiMessageDto<FindServiceDto> findService(@RequestBody @Valid FindServiceForm findServiceForm, BindingResult bindingResult) {
        ApiMessageDto<FindServiceDto> apiMessageDto = new ApiMessageDto<>();
        Service service = serviceRepository.findById(findServiceForm.getServiceId()).orElse(null);
        if (service == null){
            apiMessageDto.setMessage("service does not exist to find");
            apiMessageDto.setResult(false);
            return apiMessageDto;
        }
        List<AgencyService> agencyServiceList = agencyServiceRepository.findByServiceId(findServiceForm.getServiceId());
        double minDistance = Long.MAX_VALUE;
        Long agencyId = Long.valueOf(0);
        for(AgencyService agencyService : agencyServiceList) {
            if(Objects.equals(agencyService.getStatus(), ServiceConstant.STATUS_ACTIVE)) {
                Position position = positionRepository.findByAgencyIdAndServiceId(agencyService.getAgencyId(), agencyService.getServiceId());
                double distance = Math.sqrt(Math.pow((position.getLatitude() - findServiceForm.getLatitude()),2) + Math.pow((position.getLongitude() - findServiceForm.getLongitude()),2));
                if (distance < minDistance) {
                    minDistance = distance;
                    agencyId = position.getAgencyId();
                }
            }
        }
        Agency agency = agencyRepository.findById(agencyId).orElse(null);
        FindServiceDto findServiceDto = new FindServiceDto();
        findServiceDto.setAgencyClientDto(agencyMapper.fromEntityClientToDto(agency));
        findServiceDto.setServiceDto(serviceMapper.fromEntityToDto(service));
        apiMessageDto.setData(findServiceDto);
        apiMessageDto.setMessage("find nearest service success");
        return apiMessageDto;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.services.api.exception;

/**
 *
 * @author cht
 */
import com.fasterxml.jackson.databind.ObjectMapper;
import com.services.api.dto.ApiMessageDto;
import com.services.api.form.ErrorForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Arrays;
import java.util.List;


@ControllerAdvice
@RestController
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    final ObjectMapper mapper = new ObjectMapper();
    @ExceptionHandler({NotFoundException.class})
    public ResponseEntity<ApiMessageDto<String>> globleExcpetionHandler(NotFoundException ex) {
        log.error(""+ex.getMessage(), ex);
        ApiMessageDto<String> apiMessageDto = new ApiMessageDto<>();
        apiMessageDto.setCode("ERROR Unknow.");
        apiMessageDto.setResult(false);
        apiMessageDto.setMessage("[Ex1]: Đã có lỗi xảy ra vui lòng thử lại");
        return new ResponseEntity<>(apiMessageDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ApiMessageDto<String> apiMessageDto = new ApiMessageDto<>();
        apiMessageDto.setCode("ERROR no handler found.");
        apiMessageDto.setResult(false);
        apiMessageDto.setMessage("[Ex3]: 404");
        return new ResponseEntity<>(apiMessageDto, HttpStatus.NOT_FOUND);
    }

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public ApiMessageDto<List<ErrorForm>> exceptionHandler(Exception ex) {
        log.error(""+ex.getMessage(), ex);
		ApiMessageDto<List<ErrorForm>> apiMessageDto = new ApiMessageDto<>();
		apiMessageDto.setCode("ERROR bad request");
		apiMessageDto.setResult(false);
		if(ex instanceof MyBindingException){
            try {
                List<ErrorForm> errorForms = Arrays.asList(mapper.readValue(ex.getMessage(), ErrorForm[].class));
                apiMessageDto.setData(errorForms);
                apiMessageDto.setMessage("Invalid form");
            }catch (Exception e){
                log.error(e.getMessage(), e);
            }
        }else{
            apiMessageDto.setMessage("[Ex2]: "+ex.getMessage());
        }
		return apiMessageDto;
	}

    @ExceptionHandler({UnauthorizationException.class})
    public ResponseEntity<ApiMessageDto<String>> notAllow(UnauthorizationException ex) {
        ApiMessageDto<String> apiMessageDto = new ApiMessageDto<>();
        apiMessageDto.setResult(false);
        apiMessageDto.setMessage(ex.getMessage());
        return new ResponseEntity<>(apiMessageDto, HttpStatus.FORBIDDEN);
    }

}

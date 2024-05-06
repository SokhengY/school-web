package com.sokheng.schoolweb.controller;

import com.sokheng.schoolweb.dto.registration_dto.RegistrationRequest;
import com.sokheng.schoolweb.dto.registration_dto.RegistrationResponse;
import com.sokheng.schoolweb.exception.BadRequestException;
import com.sokheng.schoolweb.mapper.RegistrationMapper;
import com.sokheng.schoolweb.service.interfaces.RegistrationService;
import com.sokheng.schoolweb.utils.BaseResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1.0.0/registration")
public class RegistrationController {

    private final RegistrationService registrationService;
    private final RegistrationMapper registrationMapper;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public BaseResponse<List<RegistrationResponse>> register(@RequestBody @Valid RegistrationRequest request){

        if (request.getCourseId().size() == 0){
            throw new BadRequestException("course id is required");
        }
        return BaseResponse.<List<RegistrationResponse>>builder()
                .code(HttpStatus.OK.value())
                .message("success")
                .data(registrationMapper.from(registrationService.register(registrationMapper.from(request))))
                .build();
    }
}

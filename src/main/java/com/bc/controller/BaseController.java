package com.bc.controller;

import com.bc.request.CertReq;
import com.bc.response.Result;
import com.bc.service.ICertificateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/ca")
@Api(tags = "业务服务器")
public class BaseController {

    @Autowired
    private ICertificateService service;

    @PostMapping("/apply")
    @ApiResponse(code = 200, message = "OK")
    @ApiOperation(value = "apply for cert")
    private Result apply(@Valid @RequestBody CertReq req) {
        return service.applyCert();
    }

}

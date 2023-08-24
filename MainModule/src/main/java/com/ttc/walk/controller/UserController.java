package com.ttc.walk.controller;

import com.ttc.spring.controller.BaseController;
import com.ttc.spring.model.BaseResponse;
import com.ttc.walk.model.AccountClaim;
import com.ttc.walk.request.CreateUserRequest;
import com.ttc.walk.request.LoginRequest;
import com.ttc.walk.response.UserInfoResponse;
import com.ttc.walk.security.JwtProvider;
import com.ttc.walk.service.UserService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;


@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Api(tags = "API user")
public class UserController extends BaseController {
    private final UserService service;


    @PostMapping()
    public BaseResponse<UserInfoResponse> create(@Valid @RequestBody CreateUserRequest request) {
        UserInfoResponse response = service.create(request);
        return BaseResponse.ofSucceeded(response);
    }

    @GetMapping()
    public BaseResponse<UserInfoResponse> get(HttpServletRequest request) {
        UserInfoResponse response = service.get(request);
        return BaseResponse.ofSucceeded(response);
    }

    @PostMapping("/login")
    public BaseResponse<UserInfoResponse> login(@Valid @RequestBody LoginRequest request) {
        UserInfoResponse response = service.login(request);
        return BaseResponse.ofSucceeded(response);
    }

    @PostMapping("/logout")
    public BaseResponse<Void> login(HttpServletRequest request) {
        service.logout(request);
        return BaseResponse.ofSucceeded();
    }


}

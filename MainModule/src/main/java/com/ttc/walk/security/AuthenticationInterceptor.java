package com.ttc.walk.security;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ttc.spring.utils.Utility;
import com.ttc.walk.model.ApiSkipper;
import com.ttc.walk.model.message.ErrorMessage;
import com.ttc.walk.util.Constant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Slf4j
public class AuthenticationInterceptor implements HandlerInterceptor {

    private final JwtProvider jwtProvider;
    private final LoginSessionManager loginSessionManager;
    private final ObjectMapper mapper;
    private final ErrorMessage denied;

    @Autowired
    public AuthenticationInterceptor(JwtProvider jwtProvider, LoginSessionManager loginSessionManager, ObjectMapper mapper) {
        this.jwtProvider = jwtProvider;
        this.loginSessionManager = loginSessionManager;
        this.mapper = mapper;
        this.denied = new ErrorMessage("permission_denied",
                "Bạn không có quyền truy cập vào tài nguyên của hệ thống");
    }


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        if (ApiSkipper.isSkipAuthAPI(request)) return true;

        DecodedJWT decodedJWT = jwtProvider.validateJWT(request);
        if (decodedJWT == null) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            Utility.writeResponse(response, 401, mapper.writeValueAsBytes(denied));
            return false;
        }

        request.setAttribute(Constant.USER_ATTR, decodedJWT);

        String token = jwtProvider.getJWTToken(request);

        // add expire token
        loginSessionManager.saveLoginSession(decodedJWT.getClaim(Constant.USER_ID).asString(), token, null);

        return true;
    }


    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {


    }





}

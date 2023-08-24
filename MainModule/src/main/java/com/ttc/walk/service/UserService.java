package com.ttc.walk.service;

import com.ttc.walk.request.*;
import com.ttc.walk.response.*;

import javax.servlet.http.HttpServletRequest;

public interface UserService {
    UserInfoResponse create(CreateUserRequest request);
    UserInfoResponse get(HttpServletRequest request);
    UserInfoResponse login(LoginRequest request);

    void logout(HttpServletRequest request);
}

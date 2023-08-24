package com.ttc.walk.service.impl;


import com.ttc.spring.exception.BusinessException;
import com.ttc.walk.mapper.Entity2AccountAuthMapper;
import com.ttc.walk.mapper.Entity2UserInfoResponseMapper;
import com.ttc.walk.mapper.Request2UserEntityMapper;
import com.ttc.walk.model.AccountAuth;
import com.ttc.walk.model.AccountClaim;
import com.ttc.walk.model.entity.UserEntity;
import com.ttc.walk.num.AccountStatus;
import com.ttc.walk.repository.UserRepository;
import com.ttc.walk.request.*;
import com.ttc.walk.response.*;
import com.ttc.walk.security.JwtProvider;
import com.ttc.walk.security.LoginSessionManager;
import com.ttc.walk.service.UserService;
import com.ttc.walk.util.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {


    private final UserRepository repository;
    private final JwtProvider jwtProvider;
    private final LoginSessionManager loginSessionManager;
    private final PasswordEncoder passwordEncoder;


    @Override
    @Transactional
    public UserInfoResponse create(CreateUserRequest request) {
        var userExist = repository.findUserEntityByUsernameOrPhone(request.getUsername(), request.getPhone()).orElse(null);
        if (!Objects.isNull(userExist)){
            throw new BusinessException(ErrorCode.BILL_EXIST);
        }
        var userEntity = Request2UserEntityMapper.INSTANCE.map(request);
        userEntity.setHashPassword(passwordEncoder.encode(request.getPassword()));
        userEntity.setStatus(AccountStatus.ACTIVE);
        userEntity = repository.save(userEntity);
        return Entity2UserInfoResponseMapper.INSTANCE.map(userEntity);
    }
    @Override
    public UserInfoResponse get(HttpServletRequest request) {
        AccountClaim accountClaim = jwtProvider.getAccountClaim(request);
        var userEntity = repository.findById(accountClaim.getUserId()).orElseThrow(() -> new BusinessException(ErrorCode.BILL_EXIST));
        return Entity2UserInfoResponseMapper.INSTANCE.map(userEntity);
    }

    @Override
    public UserInfoResponse login(LoginRequest request) {
        var userEntity = repository.findByUsernameAndStatus(request.getUsername(), AccountStatus.ACTIVE)
                .orElseThrow(() -> new BusinessException
                        (ErrorCode.ACCOUNT_NOT_FOUND));

        validatePassword(userEntity, request);

        AccountAuth accountAuth = Entity2AccountAuthMapper.INSTANCE.map(userEntity);

        String token = jwtProvider.findOrGenerateJwt(accountAuth, 10000L);

        return Entity2UserInfoResponseMapper.INSTANCE.map(userEntity).setToken(token);
    }

    private void validatePassword(UserEntity userEntity, LoginRequest request) {
        // do validate password
    }

    @Override
    public void logout(HttpServletRequest request) {
        AccountClaim accountClaim = jwtProvider.getAccountClaim(request);
        if (Objects.isNull(accountClaim)) {
            throw new BusinessException(ErrorCode.BILL_EXIST);
        }
        loginSessionManager.revokeLoginSession(accountClaim.getUserId());
    }


}

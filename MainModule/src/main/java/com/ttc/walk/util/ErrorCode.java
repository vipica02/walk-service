package com.ttc.walk.util;


import com.ttc.spring.exception.BusinessErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.ttc.spring.utils.Constants.GROUP_CODE_BUSINESS;
import static com.ttc.spring.utils.Constants.GROUP_CODE_DATA_INVALID;


@Slf4j
@Component
public class ErrorCode {


    private ErrorCode() {
    }

    public static final BusinessErrorCode INVALID_INPUT =
            new BusinessErrorCode(4000, GROUP_CODE_DATA_INVALID, "dữ liệu nhập sai", 400);

    public static final BusinessErrorCode ACCOUNT_NOT_FOUND =
            new BusinessErrorCode(4001, GROUP_CODE_DATA_INVALID, "dữ liệu nhập sai", 400);
    public static final BusinessErrorCode BILL_EXIST =
            new BusinessErrorCode(5136, GROUP_CODE_BUSINESS,  "Có hóa đơn đã tồn tại vui lòng kiểm tra lại", 400);
    private static final Map<Integer, BusinessErrorCode> errorCodeMap;

    static {
        errorCodeMap = Arrays.stream(ErrorCode.class.getDeclaredFields())
                .filter(f -> Modifier.isStatic(f.getModifiers()) && f.getType().equals(BusinessErrorCode.class))
                .map(f -> {
                    try {
                        return (BusinessErrorCode) f.get(null);
                    } catch (IllegalAccessException e) {
                        log.error("can't load error code into map", e);
                        throw new RuntimeException(e);
                    }
                })
                .collect(Collectors.toMap(BusinessErrorCode::getCode, Function.identity()));
    }

    public static BusinessErrorCode valueOf(int errorCode) {
        return errorCodeMap.get(errorCode);
    }
}

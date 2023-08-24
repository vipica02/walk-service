package com.ttc.walk.model;

import org.springframework.http.HttpMethod;

import javax.servlet.http.HttpServletRequest;

public class ApiSkipper {

    public static final API[] skipAuthAPIs = new API[]{
            API.with("^/user/login$"),
            API.with("^/user", HttpMethod.POST)
    };

    public static boolean isSkipAuthAPI(HttpServletRequest request) {
        for (API skipAuthAPI : ApiSkipper.skipAuthAPIs) {
            if (skipAuthAPI.isSkipRequest(request)) {
                return true;
            }
        }
        return false;
    }


}

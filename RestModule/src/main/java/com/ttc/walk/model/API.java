package com.ttc.walk.model;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.http.HttpMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Pattern;

public class API {
    private Pattern pathPattern;
    private HttpMethod[] methods;
    private String path;
    private String baseUri;

    private API(Pattern pathPattern, HttpMethod[] methods) {
        this.pathPattern = pathPattern;
        this.methods = methods;
    }

    private API(Pattern pathPattern, String path, HttpMethod[] methods) {
        this.pathPattern = pathPattern;
        this.methods = methods;
        this.path = path;
    }

    public API(Pattern pathPattern, HttpMethod[] methods, String baseUri) {
        this.pathPattern = pathPattern;
        this.methods = methods;
        this.baseUri = baseUri;
    }

    public API(Pattern pathPattern, String baseUri) {
        this.pathPattern = pathPattern;
        this.baseUri = baseUri;
    }

    public String getBaseUri() {
        return baseUri;
    }

    public void setBaseUri(String baseUri) {
        this.baseUri = baseUri;
    }

    /**
     * @param pathRegex
     * @param methods
     * @return
     */
    public static API with(String pathRegex, HttpMethod... methods) {
        return new API(Pattern.compile(pathRegex), methods);
    }

    /**
     * @param pathRegex
     * @param baseUri
     * @return
     */
    public static API with(String pathRegex, String baseUri) {
        return new API(Pattern.compile(pathRegex), baseUri);
    }

    /**
     * @param pathRegex
     * @param methods
     * @return
     */
    public static API with(String pathRegex, String path, HttpMethod... methods) {
        return new API(Pattern.compile(pathRegex), path, methods);
    }

    /**
     * @param request
     * @return
     */
    public boolean isSkipRequest(HttpServletRequest request) {
        return isSkipPath(request.getRequestURI()) && isSkipMethod(request.getMethod());
    }

    private boolean isSkipPath(String path) {
        return this.pathPattern.matcher(path).matches();
    }

    public boolean isMatchPath(String path) {
        return this.pathPattern.matcher(path).matches();
    }

    public boolean isSkipMethod(String method) {
        if (this.methods == null || this.methods.length == 0) {
            return true;
        }
        for (HttpMethod httpmethod : this.methods) {
            if (httpmethod.matches(method)) {
                return true;
            }
        }
        return false;
    }

    public HttpMethod getMethod() {
        return ArrayUtils.isEmpty(methods) ? null : methods[0];
    }

    public String getPath() {
        return path;
    }
}

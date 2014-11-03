package com.h13.slg.passport.core;

import com.alibaba.fastjson.JSON;

import java.util.Map;

/**
 * Created by sunbo on 14-10-24.
 */
public class PassportResponse {
    private int code;
    private Map<String, Object> data;

    public PassportResponse(int code) {
        this.code = code;
    }

    public PassportResponse addData(String key, Object value) {
        this.data.put(key, value);
        return this;
    }

    public static PassportResponse newSuccessResponse() {
        return new PassportResponse(ResponseCode.SUCCESS);
    }

    public static PassportResponse newErrorResponse(int code) {
        return new PassportResponse(code);
    }

    public String end() {
        return JSON.toJSONString(this);
    }
}

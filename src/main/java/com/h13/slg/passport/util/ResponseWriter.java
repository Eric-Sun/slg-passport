package com.h13.slg.passport.util;

import com.h13.slg.core.exception.RequestFatalException;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by sunbo on 14-11-12.
 */
public class ResponseWriter {

    public static void write(HttpServletResponse response, String body) throws RequestFatalException {
        try {
            response.getWriter().write(body);
            response.flushBuffer();
        } catch (Exception e) {
            throw new RequestFatalException("write response error", e);
        }

    }
}

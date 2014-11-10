package com.h13.slg.passport.util;

import com.h13.slg.passport.core.PassportConstants;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by sunbo on 14-10-24.
 */
public class AccountUtil {

    /**
     * 检查account是否已经登陆
     *
     * @param request
     * @return
     */
    public static boolean checkAccountLogin(HttpServletRequest request) {
        if (request.getSession().getAttribute(PassportConstants.Session.ACCOUNT_ID_KEY) != null)
            return true;
        else
            return false;

    }

}

package com.h13.slg.passport.controller;

import com.alibaba.fastjson.JSON;
import com.h13.slg.core.exception.RequestException;
import com.h13.slg.core.exception.RequestFatalException;
import com.h13.slg.core.exception.RequestUnexpectedException;
import com.h13.slg.core.transmission.SlgData;
import com.h13.slg.passport.core.PassportConstants;
import com.h13.slg.passport.core.PassportResponse;
import com.h13.slg.passport.model.Account;
import com.h13.slg.passport.service.AccountService;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-10-5
 * Time: 下午11:19
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    AccountService accountService;

    @RequestMapping("/login")
    @ResponseBody
    public String login(HttpServletRequest request) {
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        try {
            Account account = accountService.login(name, password);
            String token = accountService.generateToken(account);
            return PassportResponse.newSuccessResponse()
                    .addData("account", account)
                    .addData("token", token)
                    .end();
        } catch (RequestException e) {
            return PassportResponse.newFailureResponse(e.getCode()).end();
        }
    }


    @RequestMapping("/register")
    @ResponseBody
    public String register(HttpServletRequest request) {
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        try {
            int id = accountService.register(name, password);
            return PassportResponse.newSuccessResponse().addData("id", id).end();
        } catch (RequestException e) {
            return PassportResponse.newFailureResponse(e.getCode()).end();
        }
    }

    @RequestMapping("/checkToken")
    @ResponseBody
    public String checkToken(HttpServletRequest request) {
        String token = request.getParameter("token");
        try {
            boolean b = accountService.checkToken(token);
            return PassportResponse.newSuccessResponse()
                    .addData("result", b == true ? 0 : -1)
                    .end();
        } catch (RequestException e) {
            return PassportResponse.newFailureResponse(e.getCode()).end();
        }
    }


}

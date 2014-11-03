package com.h13.slg.passport.controller;

import com.alibaba.fastjson.JSON;
import com.h13.slg.passport.core.PassportConstants;
import com.h13.slg.passport.core.PassportResponse;
import com.h13.slg.passport.model.Account;
import com.h13.slg.passport.service.AccountService;
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
        Account account = accountService.login(name, password);
        request.getSession().setAttribute(PassportConstants.Session.ACCOUNT_ID_KEY, account.getId());
        return PassportResponse.newSuccessResponse().addData("account",
                JSON.toJSONString(account)).end();
    }


    @RequestMapping("/register")
    @ResponseBody
    public String register(HttpServletRequest request) {
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        int id = accountService.register(name, password);
        return PassportResponse.newSuccessResponse().addData("id",
                JSON.toJSONString(id)).end();
    }
}

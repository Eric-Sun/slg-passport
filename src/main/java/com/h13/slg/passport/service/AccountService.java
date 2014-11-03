package com.h13.slg.passport.service;

import com.h13.slg.passport.dao.AccountDAO;
import com.h13.slg.passport.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-10-7
 * Time: 上午1:27
 * To change this template use File | Settings | File Templates.
 */
@Service
public class AccountService {

    @Autowired
    AccountDAO accountDAO;

    public int register(String name, String password) {

        return accountDAO.insert(name, password);
    }


    public Account login(String name, String password) {
        return accountDAO.findForLogin(name, password);
    }
}

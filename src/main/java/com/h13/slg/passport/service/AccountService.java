package com.h13.slg.passport.service;

import com.h13.slg.config.PropertiesConfiguration;
import com.h13.slg.core.exception.RequestFatalException;
import com.h13.slg.core.exception.RequestUnexpectedException;
import com.h13.slg.core.transmission.passport.PassportResponseCode;
import com.h13.slg.passport.cache.TokenCO;
import com.h13.slg.passport.cache.TokenCache;
import com.h13.slg.passport.dao.AccountDAO;
import com.h13.slg.passport.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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

    @Autowired
    TokenCache tokenCache;

    /**
     * 尝试注册
     *
     * @param name
     * @param password
     * @return
     * @throws RequestUnexpectedException
     * @throws RequestFatalException
     */
    public int register(String name, String password) throws RequestUnexpectedException, RequestFatalException {
        // 检测是否name已经被注册过
        boolean b = accountDAO.haveThisName(name);
        if (b) {
            throw new RequestUnexpectedException(PassportResponseCode.REGISTER_HAVE_THIS_NAME);
        }
        try {
            return accountDAO.insert(name, password);
        } catch (Exception e) {
            throw new RequestFatalException(PassportResponseCode.PROGRAM_ERROR,
                    String.format("name=%s,password=%s", name, password), e);
        }
    }


    /**
     * 尝试登录
     *
     * @param name
     * @param password
     * @return
     * @throws RequestUnexpectedException
     * @throws RequestFatalException
     */
    public Account login(String name, String password) throws
            RequestUnexpectedException, RequestFatalException {
        try {
            return accountDAO.findForLogin(name, password);
        } catch (DataAccessException e) {
            throw new RequestUnexpectedException(PassportResponseCode.LOGIN_FAIL);
        } catch (Exception e) {
            throw new RequestFatalException(PassportResponseCode.PROGRAM_ERROR,
                    String.format("name=%s,password=%s", name, password), e);
        }
    }


    /**
     * 获取token
     *
     * @return
     */
    public String generateToken(Account account) {
        // generate token

        String token = System.currentTimeMillis() + "";
        TokenCO tokenCO = new TokenCO();
        tokenCO.setToken(token);

        tokenCache.set(tokenCO, PropertiesConfiguration.getInstance().getIntValue("token.timeout"));

        return tokenCO.getToken();
    }


    /**
     * 游戏服务器用来检测是否token合法
     *
     * @param token
     * @return
     */
    public boolean checkToken(String token) throws RequestFatalException {
        try {

            TokenCO tokenCO = tokenCache.get(token);
            if (tokenCO != null) {
                return true;
            }
            return false;
        } catch (Exception e) {
            throw new RequestFatalException(PassportResponseCode.PROGRAM_ERROR,
                    String.format("token=%s", token), e);
        }
    }
}

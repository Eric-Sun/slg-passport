package com.h13.slg.passport.dao;

import com.h13.slg.passport.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-10-7
 * Time: 上午1:23
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class AccountDAO {

    @Autowired
    JdbcTemplate j;

    public Account findForLogin(
            String name,
            String password) {
        String sql = "select id,name from account where name=? and password=?";
        return j.queryForObject(sql, new Object[]{name, password}, new BeanPropertyRowMapper<Account>(Account.class));
    }


    public int insert(final String name, final String password) {
        KeyHolder holder = new GeneratedKeyHolder();
        final String sql = "insert into account (name,password,createtime) values (?,?,now())";
        j.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement pstmt = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
                pstmt.setString(1, name);
                pstmt.setString(2, password);
                return pstmt;
            }
        }, holder);
        return holder.getKey().intValue();
    }

    public boolean haveThisName(String name) {
        String sql = "select count(1) from account where name=?";
        int cnt = j.queryForObject(sql, new Object[]{name}, Integer.class);
        if (cnt == 0)
            return false;
        else
            return true;

    }
}

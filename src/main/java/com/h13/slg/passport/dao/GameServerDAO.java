package com.h13.slg.passport.dao;

import com.h13.slg.passport.model.GameServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-10-7
 * Time: 上午1:37
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class GameServerDAO {

    @Autowired
    JdbcTemplate j;

    public List<GameServer> list() {
        String sql = "select * from game_server";
        return j.query(sql, new Object[]{}, new BeanPropertyRowMapper<GameServer>(GameServer.class));
    }


    public int insert(final GameServer gs) {
        KeyHolder holder = new GeneratedKeyHolder();
        final String sql = "insert into game_server (name,ip,port,opentime,createtime) values (?,?,?,?,now())";
        j.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement pstmt = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
                pstmt.setString(1, gs.getName());
                pstmt.setString(2, gs.getIp());
                pstmt.setString(3, gs.getPort());
                pstmt.setDate(4, new Date(gs.getOpentime().getTime()));
                return pstmt;
            }
        }, holder);
        return holder.getKey().intValue();
    }


    public GameServer get(int gameServerId) {
        String sql = "select * from game_server where id=?";
        return j.queryForObject(sql, new Object[]{gameServerId}, GameServer.class);
    }


}
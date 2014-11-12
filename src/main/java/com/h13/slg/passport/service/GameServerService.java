package com.h13.slg.passport.service;

import com.h13.slg.passport.dao.GameServerDAO;
import com.h13.slg.core.transmission.passport.model.GameServer;
import com.h13.slg.passport.util.HttpClientUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-10-7
 * Time: 上午1:39
 * To change this template use File | Settings | File Templates.
 */
@Service
public class GameServerService {

    @Autowired
    GameServerDAO gameServerDAO;

    public List<GameServer> list() {
        return gameServerDAO.list();
    }

    public GameServer get(int gameServerId) {
        try {
            return gameServerDAO.get(gameServerId);
        } catch (DataAccessException e) {
            return null;
        }
    }

    public GameServer create(String name, String ip, int port, String openTime, int status) {
        GameServer gs = new GameServer();
        gs.setIp(ip);
        gs.setName(name);
        gs.setPort(port);
        gs.setOpentime(openTime);
        gs.setStatus(status);

        int id = gameServerDAO.insert(gs);
        gs.setId(id);

        return gs;
    }

    public GameServer update(int id, String name, String ip, int port, String openTime, int status) {
        GameServer gs = new GameServer();
        gs.setIp(ip);
        gs.setName(name);
        gs.setPort(port);
        gs.setOpentime(openTime);
        gs.setId(id);
        gs.setStatus(status);

        gameServerDAO.update(gs);
        return gs;
    }

    public void delete(int id) {
        gameServerDAO.delete(id);
    }
}

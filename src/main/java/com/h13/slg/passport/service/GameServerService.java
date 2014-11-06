package com.h13.slg.passport.service;

import com.h13.slg.passport.dao.GameServerDAO;
import com.h13.slg.passport.model.GameServer;
import com.h13.slg.passport.util.HttpClientUtil;
import org.springframework.beans.factory.annotation.Autowired;
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
        return gameServerDAO.get(gameServerId);
    }


//    public String remoteLogin(GameServer gameServer) {
//
//        String serverUrl = generateUrl(gameServer);
//
//        HttpClientUtil http = new HttpClientUtil();
//        Map<String, String> map = new HashMap<String, String>();
//        map.put("mod", "user");
//        map.put("act", "login");
//        map.put("auth_key", "fdsafdsa");
//        map.put("auth_time", "24321431");
//        map.put("args", "{name:'ssss',password:'bbbb'}");
//        map.put("seq4", "1");
//        String s = http.post(serverUrl, map);
//
//    }

    private String generateUrl(GameServer gameServer) {
        return "http://" + gameServer.getIp() + ":" + gameServer.getPort() + "/slg/";
    }


}

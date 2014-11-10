package com.h13.slg.passport.controller;

import com.alibaba.fastjson.JSON;
import com.h13.slg.core.transmission.passport.PassportResponse;
import com.h13.slg.passport.model.GameServer;
import com.h13.slg.passport.service.GameServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**

 */
@Controller
@RequestMapping("/gameserver")
public class GameServerController {

    @Autowired
    GameServerService gameServerService;


    @RequestMapping("/list")
    @ResponseBody
    public String list(HttpServletRequest request) {
        List<GameServer> list = gameServerService.list();
        return PassportResponse.newSuccessResponse().addData("list",
                list).end();
    }

}

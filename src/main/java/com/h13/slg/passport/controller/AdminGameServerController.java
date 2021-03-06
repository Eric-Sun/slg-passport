package com.h13.slg.passport.controller;

import com.alibaba.fastjson.JSON;
import com.h13.slg.core.exception.RequestFatalException;
import com.h13.slg.core.transmission.passport.PassportResponse;
import com.h13.slg.core.transmission.passport.model.GameServer;
import com.h13.slg.passport.service.GameServerService;
import com.h13.slg.passport.util.ResponseWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 用来给admin系统提供相关的服务
 * 需要通过ip白名单做验证
 */
@Controller
@RequestMapping("/admin/gameserver")
public class AdminGameServerController {

    @Autowired
    GameServerService gameServerService;

    @RequestMapping(value = "/list")
    @ResponseBody
    public String list(HttpServletRequest request, HttpServletResponse response) throws RequestFatalException {
        List<GameServer> list = gameServerService.list();
        return PassportResponse.newSuccessResponse().addData("list",
                list).end();
    }

    @RequestMapping("/create")
    @ResponseBody
    public String create(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        String ip = request.getParameter("ip");
        int port = new Integer(request.getParameter("port"));
        String openTime = request.getParameter("opentime");
        int status = new Integer(request.getParameter("status"));
        System.out.println(name);

        GameServer gs = gameServerService.create(name, ip, port, openTime, status);
        return PassportResponse.newSuccessResponse().addData("id", gs.getId()).end();
    }


    @RequestMapping("/{id}")
    @ResponseBody
    public String get(@PathVariable int id) throws RequestFatalException {
        GameServer gs = gameServerService.get(id);
        if (gs == null) {
            return PassportResponse.newSuccessResponse().end();
        }
        return PassportResponse.newSuccessResponse().addData("gameServer",
                gs).end();
    }


    @RequestMapping("/update")
    @ResponseBody
    public String update(HttpServletRequest request) {
        String name = request.getParameter("name");
        String ip = request.getParameter("ip");
        int port = new Integer(request.getParameter("port"));
        String openTime = request.getParameter("opentime");
        int id = new Integer(request.getParameter("id"));
        int status = new Integer(request.getParameter("status"));


        GameServer gs = gameServerService.update(id, name, ip, port, openTime, status);
        return PassportResponse.newSuccessResponse().end();
    }

    @RequestMapping("/delete")
    @ResponseBody
    public String delete(HttpServletRequest request) {
        int id = new Integer(request.getParameter("id"));
        gameServerService.delete(id);

        return PassportResponse.newSuccessResponse().addData("id",
                id).end();
    }
}

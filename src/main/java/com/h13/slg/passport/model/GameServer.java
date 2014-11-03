package com.h13.slg.passport.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.print.attribute.standard.DateTimeAtCompleted;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-10-7
 * Time: 上午1:36
 * To change this template use File | Settings | File Templates.
 */
public class GameServer {

    private int id;
    private String name;
    private String ip;
    private String port;
    private Date opentime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getOpentime() {
        return opentime;
    }

    public void setOpentime(Date opentime) {
        this.opentime = opentime;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }
}

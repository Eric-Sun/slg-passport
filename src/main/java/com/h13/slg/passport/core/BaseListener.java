package com.h13.slg.passport.core;

import com.h13.slg.config.PropertiesConfiguration;
import com.h13.slg.core.exception.RequestFatalException;
import com.h13.slg.core.log.SlgLogger;
import com.h13.slg.core.log.SlgLoggerEntity;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-2-11
 * Time: 下午7:14
 * To change this template use File | Settings | File Templates.
 */
public class BaseListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            PropertiesConfiguration.getInstance().addResource("/slg-passport.properties");
        } catch (RequestFatalException e) {
            e.printStackTrace();
        }
        SlgLogger.info(SlgLoggerEntity.p("base","config load",-1,"ok"));
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}

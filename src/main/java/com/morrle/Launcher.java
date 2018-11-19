package com.morrle;

import io.netty.util.internal.logging.InternalLoggerFactory;
import io.netty.util.internal.logging.Log4JLoggerFactory;
import io.vertx.core.Vertx;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 * @author morrle
 * @date 2018/10/28 7:33
 **/
public class Launcher {

    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        System.setProperty(" vertx.logger-delegate-factory-class-name","io.vertx.core.logging.SLF4JLogDelegateFactory");
        InternalLoggerFactory.setDefaultFactory(Log4JLoggerFactory.INSTANCE);

        Vertx.vertx().deployVerticle(MainVerticle.class.getName(), res -> {
            if (res.succeeded()) {
                logger.info("MainVerticle: {}", res.result());
            } else {
                logger.warn("");
            }
        });

    }





}

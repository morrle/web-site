package com.morrle;

import com.morrle.model.entity.Attach;
import com.morrle.router.ArticlesRouter;
import com.morrle.units.BeanUtils;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.mongo.MongoClient;
import io.vertx.ext.web.Route;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.Session;
import io.vertx.ext.web.handler.FaviconHandler;
import io.vertx.ext.web.handler.SessionHandler;
import io.vertx.ext.web.handler.StaticHandler;
import io.vertx.ext.web.sstore.LocalSessionStore;
import io.vertx.ext.web.templ.ThymeleafTemplateEngine;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 * @author morrle
 * @date 2018/10/27 13:51
 **/
public class MainVerticle extends AbstractVerticle {
    private static final Logger logger = LogManager.getLogger();

    private MongoClient mongoClient;

    @Override
    public void start() {
        final Router router = Router.router(vertx);
        final ThymeleafTemplateEngine engine = ThymeleafTemplateEngine.create();
        final SessionHandler sessionHandler = SessionHandler.create(LocalSessionStore.create(vertx));


        mongoClient = MongoClient.createShared(vertx, config());

        router.route("/static/*").handler(StaticHandler.create());
        router.route("/favicon.ico").handler(FaviconHandler.create("webroot/favicon.ico"));
        router.route().handler(sessionHandler);

        Route route = router.routeWithRegex("/.*").handler(StaticHandler.create()).handler(ctx -> {
            logger.info("来访地址 : {},访问路径 : {}", ctx.request().remoteAddress().host(), ctx.request().path());
            Session session = ctx.session();
            session.put("ip", ctx.request().remoteAddress().host());
            ctx.next();
        });

        router.route("/").handler(ctx -> {
            engine.render(ctx, "templates/site/", "index.html", res -> {
                if (res.succeeded()) {
                    logger.info("来访地址 : {},访问路径 : {}", ctx.request().remoteAddress().host(), ctx.request().path());
                    ctx.response().end(res.result());
                } else {
                    ctx.fail(res.cause());
                }
            });
        });

        router.routeWithRegex("/api/.*").handler(ctx -> {
            logger.info("/api/...");

            ctx.next();
        });

        router.post("/api/attach").handler(ctx -> {
            Attach attach = new Attach();
            attach.setPath("111");
            attach.setName("斯蒂芬斯的");
            mongoClient.save("Attach", BeanUtils.beanToJson(attach), res -> {
                if (res.succeeded()) {
                    logger.info("saved id:{}", res.result());
                } else {
                    logger.error("保存失败");
                }
                ctx.response().end(res.result());
            });
        });
        router.get("/api/attach/:id").handler(ctx -> {
            //5bdea0990e6073257dd73fec
            String id = ctx.request().getParam("id");
            Session session = ctx.session();
            String ip = session.get("ip");
            System.out.println("session: " + ip);

            JsonObject query = new JsonObject().put("_id", id);
            mongoClient.findOne("Attach", query, null, res -> {
                if (res.succeeded()) {
                    String string = res.result().toString();
                    ctx.response().putHeader("Content-Type", "text/html; charset=utf-8").end(string);
                } else {
                    ctx.response().end();
                }

            });
        });

        //异常处理
        route.failureHandler(frc -> {
            int statusCode = frc.statusCode() == -1 ? 500 : frc.statusCode();
            logger.error("请求异常,{} 请求地址: {},状态码:{}", frc.request().remoteAddress(), frc.request().path(), statusCode);
            frc.response().setStatusCode(statusCode).end("Sorry! Not today");
        });

        // start a HTTP web server on port 8080
        vertx.createHttpServer().requestHandler(router::accept)
                .requestHandler(ArticlesRouter.router(router, engine, mongoClient)::accept)
                .listen(8080);
    }

}

package com.morrle.router;

import io.vertx.core.json.JsonObject;
import io.vertx.ext.mongo.MongoClient;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.templ.ThymeleafTemplateEngine;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author morrle
 * @date 2018/11/19 17:27
 * ArticleRouter
 **/
public class ArticlesRouter {
    private static final Logger logger = LogManager.getLogger();

    private static final String COLLECTION = "Articles";

    public static Router router(Router router, ThymeleafTemplateEngine engine, MongoClient client) {
        router.get("/p/:id").handler(ctx -> {
            String id = ctx.request().getParam("id");
            client.findOne(COLLECTION, new JsonObject().put("_id", id), null, res -> {
                if (res.succeeded()) {
                    JsonObject result = res.result();
                    ctx.put("contents",result);
                    engine.render(ctx, "templates/site/", "article.html", asyncResult -> {
                        if (asyncResult.succeeded()) {
                            ctx.response().end(asyncResult.result());
                        } else {
                            ctx.fail(asyncResult.cause());
                        }
                    });
                } else {
                    ctx.fail(500);
                }
            });

        });

        router.route("/admin").handler(ctx -> {
            engine.render(ctx, "templates/admin/", "index.html", asyncResult -> {
                if (asyncResult.succeeded()) {
                    ctx.response().end(asyncResult.result());
                } else {
                    ctx.fail(asyncResult.cause());
                }
            });
        });


        return router;
    }

}

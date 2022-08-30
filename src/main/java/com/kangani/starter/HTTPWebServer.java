package com.kangani.starter;

import com.kangani.starter.eventbus.EventbusImpl;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Route;
import io.vertx.ext.web.Router;

import static com.kangani.starter.Main.eb;

public class HTTPWebServer {
  public static void init() {
    Vertx vertx = Vertx.vertx();
    HttpServer server = vertx.createHttpServer();
    Router router = Router.router(vertx);
    Route route = router.route(HttpMethod.POST, "/request");
    route.handler(ctx -> {
      HttpServerResponse response = ctx.response();
      eb.publish("eventbus.request", response.toString());
      response.end("You request has been taken for processing...");
    });
    server.requestHandler(router).listen(4567);
  }
}

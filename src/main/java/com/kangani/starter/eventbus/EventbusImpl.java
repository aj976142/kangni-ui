package com.kangani.starter.eventbus;

import io.vertx.core.Vertx;
import io.vertx.core.eventbus.EventBus;

public class EventbusImpl {
  public static EventBus init() {
    Vertx vertx = Vertx.vertx();
    EventBus eb = vertx.eventBus();
    return eb;
  }
}

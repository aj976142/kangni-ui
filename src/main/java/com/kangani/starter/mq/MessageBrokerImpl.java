package com.kangani.starter.mq;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.rabbitmq.RabbitMQClient;
import io.vertx.rabbitmq.RabbitMQOptions;

public class MessageBrokerImpl {

  public static RabbitMQClient init() throws InterruptedException {
    Vertx vertx = Vertx.vertx();
    RabbitMQOptions config = new RabbitMQOptions();
// Each parameter is optional
// The default parameter with be used if the parameter is not set
    config.setUser("admin");
    config.setPassword("Admin@123");
    config.setHost("localhost");
    config.setPort(5672);
    config.setVirtualHost("/");
    config.setConnectionTimeout(6000); // in milliseconds
    config.setRequestedHeartbeat(60); // in seconds
    config.setHandshakeTimeout(6000); // in milliseconds
    config.setRequestedChannelMax(5);
    config.setNetworkRecoveryInterval(500); // in milliseconds
    config.setAutomaticRecoveryEnabled(true);

    RabbitMQClient client = RabbitMQClient.create(vertx, config);

// Connect
    client.start(asyncResult -> {
      if (asyncResult.succeeded()) {
        System.out.println("RabbitMQ successfully connected!");

      } else {
        System.out.println("Fail to connect to RabbitMQ " + asyncResult.cause().getMessage());
      }
    });
    Thread.sleep(5000);
    return client;
  }
}

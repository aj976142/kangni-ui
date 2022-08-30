package com.kangani.starter;

import com.kangani.starter.eventbus.EventbusImpl;
import com.kangani.starter.mq.MessageBrokerImpl;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.EventBus;
import io.vertx.rabbitmq.RabbitMQClient;

public class Main {

  static EventBus eb;
  static RabbitMQClient rmqClient;

  public static void main(String[] args) throws InterruptedException {
    //Start webserver
    HTTPWebServer.init();
    rmqClient = MessageBrokerImpl.init();
    eb = EventbusImpl.init();
    eb.consumer("eventbus.request", message -> {
      rmqClient.basicPublish("", "com.kangani.request", Buffer.buffer(message.body().toString()), pubResult -> {
        if (pubResult.succeeded()) {
          System.out.println("Message published !");
        } else {
          pubResult.cause().printStackTrace();
        }
      });
      System.out.println("Waiting to be published!!!!");
    });
  }
}

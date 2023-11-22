package com.example.starter.eventbus.requestresponse;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;

public class PointToPointExample {
  public static void main(String[] args) {
    var vertex = Vertx.vertx();
    vertex.deployVerticle(new Sender());
    vertex.deployVerticle(new Receiver());
  }
  static class Sender extends AbstractVerticle {
    @Override
    public void start(Promise<Void> startPromise) throws Exception {
      startPromise.complete();
      var eventBus = vertx.eventBus();
      vertx.setPeriodic(1000,id->{
        eventBus.send(Sender.class.getName(),"Hello");
      });
    }
  }
  static class Receiver extends AbstractVerticle {
    @Override
    public void start(Promise<Void> startPromise) throws Exception {
      startPromise.complete();
      var eventBus = vertx.eventBus();
      eventBus.<String>consumer(Sender.class.getName(),message->{
        System.out.println("Message: "+message.body());
      });
    }
  }
}

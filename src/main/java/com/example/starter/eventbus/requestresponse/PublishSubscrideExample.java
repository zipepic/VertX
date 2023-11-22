package com.example.starter.eventbus.requestresponse;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;

public class PublishSubscrideExample {
  public static void main(String[] args) {
    var vertex = Vertx.vertx();
    vertex.deployVerticle(new Publish());
    vertex.deployVerticle(new Subscribe1());
    vertex.deployVerticle(new Subscribe2());
  }
  static class Publish extends AbstractVerticle {
    @Override
    public void start(Promise<Void> startPromise) throws Exception {
      startPromise.complete();
      vertx.setPeriodic(1000,id->{
        vertx.eventBus().publish(Publish.class.getName(),"Hello");
      });
    }
  }
  static class Subscribe1 extends AbstractVerticle {
    @Override
    public void start(Promise<Void> startPromise) throws Exception {
      startPromise.complete();
      vertx.eventBus().<String>consumer(Publish.class.getName(),message->{
        System.out.println("Message in Subscribe1: "+message.body());
      });
    }
  }
  static class Subscribe2 extends AbstractVerticle {
    @Override
    public void start(Promise<Void> startPromise) throws Exception {
      startPromise.complete();
      vertx.eventBus().<String>consumer(Publish.class.getName(),message->{
        System.out.println("Message in Subscribe2: "+message.body());
      });
    }
  }
}

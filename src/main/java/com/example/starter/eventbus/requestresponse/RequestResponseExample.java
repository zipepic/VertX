package com.example.starter.eventbus.requestresponse;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;

public class RequestResponseExample {

  public static final String ADDRESS = "myaddress.request.address";

  public static void main(String[] args) {
    var vertx = Vertx.vertx();
    vertx.deployVerticle(new RequestVertical());
    vertx.deployVerticle(new ResponseVertical());
  }
  static class RequestVertical extends AbstractVerticle {
    @Override
    public void start(io.vertx.core.Promise<Void> startPromise) throws Exception {
      startPromise.complete();
      var eventBus = vertx.eventBus();
      eventBus.<String>request(ADDRESS,"Hello", reply->{
        System.out.println("Response: "+reply.result().body());
      });
    }
  }

  static class ResponseVertical extends AbstractVerticle{
    @Override
    public void start(Promise<Void> startPromise) throws Exception {
      startPromise.complete();
      var eventBus = vertx.eventBus();
      eventBus.<String>consumer(ADDRESS,message->{
        System.out.println("Request: "+message.body());
        message.reply("World");
      });
    }
  }

}

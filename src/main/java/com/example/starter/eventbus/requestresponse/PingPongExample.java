package com.example.starter.eventbus.requestresponse;

import io.vertx.core.*;

public class PingPongExample {

  public static final String ADDRESS = "myaddress.request.address";

  public static void main(String[] args) {
    var vertx = Vertx.vertx();
    vertx.deployVerticle(new PingVertical(), getAsyncResultHandler());
    vertx.deployVerticle(new PongVertical(), getAsyncResultHandler());
  }

  private static Handler<AsyncResult<String>> getAsyncResultHandler() {
    return ar -> {
      if (ar.failed()) {
        System.out.println("Deployment failed" + ar.cause());
      }
    };
  }

  static class PingVertical extends AbstractVerticle {
    @Override
    public void start(Promise<Void> startPromise) throws Exception {
      vertx.eventBus().registerDefaultCodec(Ping.class, new MyCodec<>(Ping.class));
      startPromise.complete();
      var eventBus = vertx.eventBus();
      var ping = new Ping("Hello",true);
      eventBus.<Pong>request(ADDRESS,ping, reply->{
        if(reply.failed()){
          System.out.println("Request failed");
        }
        System.out.println("Response: "+reply.result().body());
      });
    }
  }

  static class PongVertical extends AbstractVerticle{
    @Override
    public void start(Promise<Void> startPromise) throws Exception {
      vertx.eventBus().registerDefaultCodec(Pong.class, new MyCodec<>(Pong.class));
      startPromise.complete();
      var eventBus = vertx.eventBus();
      eventBus.<Ping>consumer(ADDRESS,message->{
        System.out.println("Request: "+message.body());
        message.reply(new Pong(0));
      }).exceptionHandler(throwable -> {
        System.out.println("Exception: "+throwable.getCause());
      });
    }
  }

}

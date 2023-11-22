package com.example.starter.verticle;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;

public class MainVerticale extends AbstractVerticle {
  public static void main(String[] args) {
    var vertx = io.vertx.core.Vertx.vertx();
    vertx.deployVerticle(new MainVerticale());
  }
  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    System.out.println("Hello from MainVerticale!");
    vertx.deployVerticle(new VerticaleA(),whenDeploy->{
      System.out.println("VerticaleA deployed");
      vertx.undeploy(whenDeploy.result());
    });
    vertx.deployVerticle(new VerticaleB());
    startPromise.complete();
  }
}

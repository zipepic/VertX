package com.example.starter.verticle;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;

public class VerticaleB extends AbstractVerticle {
  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    System.out.println("Hello from VerticaleB!");
    startPromise.complete();
  }
}

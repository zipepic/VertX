package com.example.starter.verticle;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;

public class VerticaleA extends AbstractVerticle {
  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    System.out.println("Hello from VerticaleA!");
    startPromise.complete();
  }

  @Override
  public void stop(Promise<Void> stopPromise) throws Exception {
    System.out.println("Bye from VerticaleA!");
    stopPromise.complete();
  }
}

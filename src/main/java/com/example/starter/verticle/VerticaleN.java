package com.example.starter.verticle;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;

public class VerticaleN extends AbstractVerticle {
  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    System.out.println("Hello from VerticaleN!" + "thread name -> " + Thread.currentThread().getName());
    startPromise.complete();
  }
}

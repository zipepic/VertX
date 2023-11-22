package com.example.starter.worker;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;

public class WorkerVericale extends AbstractVerticle {
  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    System.out.println("Hello from WorkerVericale!");
    startPromise.complete();
    Thread.sleep(5000);
    System.out.println("WorkerVericale finished");

  }
}

package com.example.starter.eventloop;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;

import java.util.concurrent.TimeUnit;

public class ExampleEventLoop extends AbstractVerticle {
  public static void main(String[] args) {
    var vertx = Vertx.vertx(new VertxOptions()
      .setMaxEventLoopExecuteTime(500)
      .setMaxEventLoopExecuteTimeUnit(TimeUnit.MILLISECONDS)
      .setBlockedThreadCheckInterval(1)
      .setBlockedThreadCheckIntervalUnit(TimeUnit.SECONDS));
    vertx.deployVerticle(new ExampleEventLoop());
  }

  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    startPromise.complete();
    Thread.sleep(5000);
  }
}

package com.example.starter.worker;

import io.vertx.core.*;

public class WorkerExample extends AbstractVerticle {
  public static void main(String[] args) {
    var vertx = Vertx.vertx();
    vertx.deployVerticle(new WorkerExample());
  }

  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    vertx.deployVerticle(new WorkerVericale(),
      new DeploymentOptions().setWorker(true)
        .setWorkerPoolName("my-worker-pool")
        .setWorkerPoolSize(1)
      );
    startPromise.complete();
    vertx.executeBlocking(event->{
      try {
        System.out.println("Blocking code executed");
        Thread.sleep(5000);
        event.complete();
      } catch (InterruptedException e) {
        event.fail(e);
      }
    },result->{
      if(result.succeeded()){
        System.out.println("Blocking code executed successfully");
      }else {
        System.out.println("Blocking code executed failed");
      }
    });
  }
}

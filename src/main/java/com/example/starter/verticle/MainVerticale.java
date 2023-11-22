package com.example.starter.verticle;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Promise;
import io.vertx.core.json.JsonObject;


import java.util.UUID;

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
    var config = new JsonObject().put("ID", UUID.randomUUID().toString()).put( "name", "verticaleB");
    vertx.deployVerticle(new VerticaleB(), new DeploymentOptions().setConfig(config));
    vertx.deployVerticle(VerticaleN.class.getName(),new DeploymentOptions().setInstances(4));
    startPromise.complete();
  }
}

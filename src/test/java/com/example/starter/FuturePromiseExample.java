package com.example.starter;

import io.vertx.core.CompositeFuture;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.junit5.VertxExtension;
import io.vertx.junit5.VertxTestContext;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(VertxExtension.class)
public class FuturePromiseExample {
  @Test
  void promiseSuccess(Vertx vertx, VertxTestContext context) {
    Promise<String> promise = Promise.promise();
    System.out.println("Start");
    vertx.setTimer(500, id -> {
      promise.complete("Success");
      System.out.println("End");
      context.completeNow();
    });
    System.out.println("After setTimer");
  }
  @Test
  void promiseFailure(Vertx vertx, VertxTestContext context) {
    Promise<String> promise = Promise.promise();
    System.out.println("Start");
    vertx.setTimer(500, id -> {
      promise.fail("Failure");
      System.out.println("End");
      context.completeNow();
    });
    System.out.println("After setTimer");
  }
  @Test
  void futureSuccess(Vertx vertx, VertxTestContext context) {
    Promise<String> promise = Promise.promise();
    System.out.println("Start");//1
    vertx.setTimer(500, id -> {
      promise.complete("Success");
      System.out.println("End");//4
    });
    final Future<String> future = promise.future();
    future.onSuccess(result -> {
      System.out.println("Future Success: " + result);//3
      context.completeNow();
    }).onFailure(context::failNow);
    System.out.println("After setTimer");//2
  }
  @Test
  void futureFailure(Vertx vertx, VertxTestContext context) {
    Promise<String> promise = Promise.promise();
    System.out.println("Start");//1
    vertx.setTimer(500, id -> {
      promise.fail("Failure");
      System.out.println("End");//4
    });
    final Future<String> future = promise.future();
    future.onSuccess(context::failNow).onFailure(error->{
      System.out.println("Future Failure: "+error.getMessage());//3
      context.completeNow();
    });
    System.out.println("After setTimer");//2
  }
  @Test
  void futureMap(Vertx vertx, VertxTestContext context) {
    final Promise<String> promise = Promise.promise();

    System.out.println("Start");//1

    vertx.setTimer(500, id -> {
      promise.complete("Success");
      System.out.println("End");//4
    });

    final Future<String> future = promise.future();

    future
      .map(String::toUpperCase)
      .map(asString -> new JsonObject().put("key", asString))
      .map(jsonObject -> new JsonArray().add(jsonObject))
      .onSuccess(result -> {
      System.out.println("Future Success: " + result);//3
      context.completeNow();})
      .onFailure(context::failNow);

    System.out.println("After setTimer");//2
  }
  @Test
  void simpleCoordination(Vertx vertx, VertxTestContext context) {
    vertx.createHttpServer()
      .requestHandler(req -> System.out.println(req))
      .listen(8080)
      .compose(server -> {
        System.out.println("Server started");
        return Future.succeededFuture(server);})
      .compose(server -> {
        System.out.println("Server second started");
        return Future.succeededFuture(server);})
      .onSuccess(server -> {
        System.out.println("Server third started");
        context.completeNow();})
      .onFailure(context::failNow);

  }
  @Test
  void simpleComposite(Vertx vertx,VertxTestContext context) {
    var promise1 = Promise.<Void>promise();
    var promise2 = Promise.<Void>promise();
    var promise3 = Promise.<Void>promise();

    var future1 = promise1.future();
    var future2 = promise2.future();
    var future3 = promise3.future();

    CompositeFuture.all(future1,future2,future3)
      .onSuccess(result->{
        System.out.println("All success");
        context.completeNow();
      })
      .onFailure(context::failNow);

    vertx.setTimer(500,id->{
      promise1.complete();
      promise2.complete();
      promise3.fail("Failure");
    });
  }
}

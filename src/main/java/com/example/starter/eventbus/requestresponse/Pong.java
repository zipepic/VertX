package com.example.starter.eventbus.requestresponse;

public class Pong {
  private final int id;

  public Pong(int id) {
    this.id = id;
  }

  public final int getId() {
    return id;
  }

  @Override
  public String toString() {
    return "Pong{" +
      "id=" + id +
      '}';
  }
}

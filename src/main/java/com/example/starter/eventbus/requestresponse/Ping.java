package com.example.starter.eventbus.requestresponse;

public class Ping {
  private final String message;
  private final boolean enabled;

  public Ping(String message, boolean enabled) {
    this.message = message;
    this.enabled = enabled;
  }

  public final String getMessage() {
    return message;
  }

  public final boolean isEnabled() {
    return enabled;
  }

  @Override
  public String toString() {
    return "Ping{" +
      "message='" + message + '\'' +
      ", enabled=" + enabled +
      '}';
  }
}

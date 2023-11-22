package com.example.starter.eventbus.requestresponse;

import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.MessageCodec;

public final class MyCodec<T> implements MessageCodec<T, T> {
  private final String typeName;

  public MyCodec(Class<T> type) {
    this.typeName = type.getTypeName();
  }

  @Override
  public void encodeToWire(Buffer buffer, T t) {
    throw new UnsupportedOperationException();
  }

  @Override
  public T decodeFromWire(int i, Buffer buffer) {
    throw new UnsupportedOperationException();
  }

  @Override
  public T transform(T t) {
    return t;
  }

  @Override
  public String name() {
    return this.typeName;
  }

  @Override
  public byte systemCodecID() {
    return -1;
  }
}

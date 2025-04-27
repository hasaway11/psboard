package com.example.psboard.entity;

public enum Level {
  NORMAL("고마운 분"), SILVER("귀한 분"), GOLD("천생연분");

  private final String name;

  public String getName() {
    return name;
  }

  Level(String name) {
    this.name = name;
  }
}

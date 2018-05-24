package com.company;

public class Pj {
  static   String send(String command) {
        System.out.println("hhhmmmm who is it");
        if (command.startsWith("pw")) {
            return Heroes.playWith();
        } else return "bitch";
    }
}

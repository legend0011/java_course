package com.example.java_course;

public class Play implements Runnable {

  @Override
  public void run() {
    System.out.println("in thread 1");
  }

  public static void main(String args[]) {
    Play p = new Play();
    Thread t = new Thread(p);
    t.start();
  }
}

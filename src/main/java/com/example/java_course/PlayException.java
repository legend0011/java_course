package com.example.java_course;

import java.io.IOException;

class AException extends Exception{
  AException(String msg) {
    super(msg);
  }
}



class A {
  public void a() throws AException {
    throw new AException("a() wrong!");
  }
}

class BException extends Exception {
  BException(String msg, Exception e) {
    super(msg, e);
  }
}

class B {
  public void b() throws BException {
    try {
      A a = new A();
      a.a();
    } catch (AException e) {
      throw new BException("b() wrong!", e);
    }
  }
}

class ApiException extends Exception {
  ApiException(String msg, Exception e) {
    super(msg, e);
  }
}
class Api {
  public void api() throws ApiException {
    try {
      B b = new B();
      b.b();
    } catch (BException bx) {
      System.out.println("something wrong: " + bx);
    }
  }
}

public class PlayException {
  public static void main(String args[]) {
    try {
      B b = new B();
      b.b();
    } catch (BException be) {
      System.out.println("===>" + be.toString());
    }
  }
}

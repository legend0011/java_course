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
      throw new ApiException("api() wrong:", bx);
    }
  }
}

public class PlayException {
  public static void main(String args[]) {
    try {
      Api api = new Api();
      api.api();
    } catch (ApiException ae) {
      System.out.println("something wrong: " + ae);
      System.out.println("caused by: ");
      ae.printStackTrace();
    }
  }
}

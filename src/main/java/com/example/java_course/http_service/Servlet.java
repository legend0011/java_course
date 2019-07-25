package com.example.java_course.http_service;

import java.io.OutputStream;

public interface Servlet {
  void init();
  void service( String params, byte [] requestBuffer, OutputStream output);
}

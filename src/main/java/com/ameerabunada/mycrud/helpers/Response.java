package com.ameerabunada.mycrud.helpers;

import java.util.HashMap;

import org.springframework.http.ResponseEntity;

public class Response {

  private Response() {

  }

  public static ResponseEntity<?> success(int statusCode, String message, Object data) {
    // Create a response object
    HashMap<String, Object> body = new HashMap<>();
    body.put("status", true);
    body.put("message", message);
    body.put("data", data);

    // Return the response entity
    return ResponseEntity.status(statusCode).body(body);
  }

  public static ResponseEntity<?> error(int statusCode, String message, Object data) {
    // Create a response object
    HashMap<String, Object> body = new HashMap<>();
    body.put("status", false);
    body.put("message", message);
    body.put("data", data);

    // Return the response entity
    return ResponseEntity.status(statusCode).body(body);
  }
}

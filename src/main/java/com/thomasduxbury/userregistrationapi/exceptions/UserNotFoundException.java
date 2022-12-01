package com.thomasduxbury.userregistrationapi.exceptions;

public class UserNotFoundException extends RuntimeException {

  public UserNotFoundException(long id) {
    super("User not found with id: " + id);
  }
}

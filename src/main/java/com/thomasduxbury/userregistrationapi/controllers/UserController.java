package com.thomasduxbury.userregistrationapi.controllers;

import com.thomasduxbury.userregistrationapi.domain.User;
import com.thomasduxbury.userregistrationapi.exceptions.UserNotFoundException;
import com.thomasduxbury.userregistrationapi.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

  private final UserRepository userRepository;

  public UserController(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @CrossOrigin(origins = "http://localhost:3000")
  @PostMapping("/users")
  public void saveUser(@Valid @RequestBody User user) {
    userRepository.save(user);
  }

  @CrossOrigin(origins = "http://localhost:3000")
  @GetMapping("/users")
  public List<User> getUsers() {
    return userRepository.findAll();
  }

  @CrossOrigin(origins = "http://localhost:3000")
  @GetMapping("/users/{id}")
  public User getUser(@PathVariable Long id) {
    return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
  }

  @CrossOrigin(origins = "http://127.0.0.1:3000")
  @DeleteMapping("/users/{id}")
  void deleteUser(@PathVariable Long id) {
    userRepository.deleteById(id);
  }
}

package com.rachvik.jooq.controller;

import com.rachvik.jooq.sample.model.tables.pojos.Users;
import com.rachvik.jooq.service.UserService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  @PostMapping
  public ResponseEntity<Users> createUser(@RequestBody Users user) {
    return ResponseEntity.ok(userService.create(user));
  }

  @GetMapping
  public ResponseEntity<Users> get(@RequestParam(name = "username") final String username) {
    return ResponseEntity.ok(userService.get(username).orElse(null));
  }

  @GetMapping("/all")
  public ResponseEntity<List<Users>> getAll() {
    return ResponseEntity.ok(userService.getAll());
  }
}

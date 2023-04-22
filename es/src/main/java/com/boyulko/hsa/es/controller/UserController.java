package com.boyulko.hsa.es.controller;

import com.boyulko.hsa.es.model.User;
import com.boyulko.hsa.es.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

  @Autowired
  private UserService userService;

  @GetMapping
  public List<User> getAllUsers() {
    return this.userService.listAll();

  }

  @DeleteMapping
  public void delete() {
    this.userService.deleteAll();
  }

  @GetMapping(path = "/search")
  public List<User> searchUsers(@RequestParam String keywords) {
    return this.userService.search(keywords);
  }
}

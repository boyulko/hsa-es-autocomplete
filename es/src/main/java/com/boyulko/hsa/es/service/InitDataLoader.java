package com.boyulko.hsa.es.service;

import com.boyulko.hsa.es.dto.UserDto;
import com.boyulko.hsa.es.model.User;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class InitDataLoader implements CommandLineRunner {

  @Autowired
  private UserService userService;

  @Override
  public void run(String... args) throws Exception {

    List<User> users = this.createUsers();

    userService.saveAll(users);
  }

  private List<User> createUsers() {

    var allFakeUsers = List.of(
        UserDto.builder()
            .name("Anita")
            .last("Costea")
            .build(),
        UserDto.builder()
            .name("Angela")
            .last("Mist")
            .build(),
        UserDto.builder()
            .name("Brandon")
            .last("Robertson")
            .build(),
        UserDto.builder()
            .name("Angela")
            .last("Rovertsen")
            .build(),
        UserDto.builder()
            .name("Mike")
            .last("Riko")
            .build(),
        UserDto.builder()
            .name("Michael")
            .last("Rina")
            .build()
    );
    return allFakeUsers.stream().map(this::from).map(this::generateId).collect(
        Collectors.toList());
  }

  private User generateId(User user) {
    user.setId(UUID.randomUUID().toString());
    return user;
  }

  private User from(UserDto userDto) {
    User user = new User();
    user.setFirstName(userDto.getName());
    user.setLast(userDto.getLast());
    return user;
  }

}

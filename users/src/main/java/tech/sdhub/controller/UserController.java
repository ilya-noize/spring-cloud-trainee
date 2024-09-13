package tech.sdhub.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tech.sdhub.dto.NewUserDto;
import tech.sdhub.model.User;
import tech.sdhub.service.UserService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@Slf4j
public class UserController {
    private final UserService userService;

    @PostMapping
    public User create(@RequestBody NewUserDto user) {
        log.info("Create user: {}", user);

        return userService.create(user);
    }

    @PutMapping("{id}")
    public User updateUser(@PathVariable Long id,
                           @RequestBody NewUserDto user) {
        log.info("Update user with id {}", id);

        return userService.updateUserById(id, user);
    }

    @GetMapping("{id}")
    public User getById(@PathVariable Long id) {
        log.info("Get user ID:{}", id);

        return userService.getById(id);
    }


    @GetMapping
    public List<User> getAll(@RequestParam(required = false, name = "id") List<Long> ids) {
        log.info("getAll users: {}", ids);

        return userService.getAll(ids);
    }

    @DeleteMapping("{id}")
    public void deleteById(@PathVariable Long id) {
        userService.deleteById(id);
    }
}

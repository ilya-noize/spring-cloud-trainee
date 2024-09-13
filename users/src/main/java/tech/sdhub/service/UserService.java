package tech.sdhub.service;

import tech.sdhub.dto.NewUserDto;
import tech.sdhub.model.User;

import java.util.List;

public interface UserService {
    User create(NewUserDto user);

    User updateUserById(Long id, NewUserDto user);

    User getById(Long id);

    List<User> getAll(List<Long> ids);

    void deleteById(Long id);
}

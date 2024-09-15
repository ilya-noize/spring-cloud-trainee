package tech.sdhub.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.sdhub.dto.NewUserDto;
import tech.sdhub.exception.BadRequestException;
import tech.sdhub.exception.NotFoundException;
import tech.sdhub.repository.UserRepository;
import tech.sdhub.model.User;

import java.util.List;

import static java.lang.String.format;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public User create(NewUserDto user) {
        String userPhone = user.getPhone();
        isUniquePhone(userPhone);
        User entity = User.builder()
                .name(user.getName())
                .surname(user.getSurname())
                .phone(userPhone)
                .build();

        return userRepository.save(entity);
    }

    @Override
    public User updateUserById(Long id, NewUserDto user) {
        User existedUser = getUser(id);
        String newerPhoneParameter = getNewerParameter(user.getPhone(), existedUser.getPhone());
        if (newerPhoneParameter.equals(user.getPhone()))
            isUniquePhone(newerPhoneParameter);
        User updateUser = User.builder()
                .id(id)
                .name(getNewerParameter(user.getName(), existedUser.getName()))
                .surname(getNewerParameter(user.getSurname(), existedUser.getSurname()))
                .phone(newerPhoneParameter)
                .build();

        return userRepository.save(updateUser);
    }

    @Override
    public User getById(Long id) {

        return getUser(id);
    }

    @Override
    public List<User> getAll(List<Long> ids) {
        if (ids == null)
            return userRepository.findAll();

        return userRepository.findAllById(ids);
    }

    @Override
    public void deleteById(Long id) {
        User user = getUser(id);
        userRepository.delete(user);
    }

    private String getNewerParameter(String newValue, String existedValue) {

        return newValue == null || newValue.equals(existedValue) ? existedValue : newValue;
    }

    private void isUniquePhone(String phone) {
        if (userRepository.existsByPhoneIgnoreCase(phone))
            throw new BadRequestException(format("Phone must be unique, but %s was exist.", phone));

    }

    private User getUser(Long id) {
        return userRepository.findById(id).orElseThrow(
                () -> new NotFoundException(format("User with ID %s not found", id))
        );
    }
}

package co.com.poli.userservice.service;

import co.com.poli.userservice.persistence.entity.User;
import co.com.poli.userservice.service.dto.UserDto;

import java.util.List;

public interface UserService {

    void save(UserDto userDto);
    void delete(User user);
    List<User> findAll();
    User findById(Long id);
}

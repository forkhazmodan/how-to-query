package com.example.howtoquery.service;

import com.example.howtoquery.model.User;
import com.example.howtoquery.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service("userServiceV2")
public class UserServiceV2_NativeQueryExample implements UserService {

    public final UserRepository userRepository;

    public UserServiceV2_NativeQueryExample(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Page<User> searchUsers(
            String name,
            Integer age,
            Date createdFrom,
            Date createdTo,
            Pageable pageable) {

        return userRepository.searchUsers(name, age, createdFrom, createdTo, pageable);
    }

    public User searchUser(Long id) {
        return userRepository.getById(id);
    }

    @Override
    public User createUser(User user) {
        return null;
    }

    @Override
    public User updateUser(User user) {
        return null;
    }

    public void deleteUser(Long id) {

    }
}

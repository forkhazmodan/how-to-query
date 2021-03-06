package com.example.howtoquery.service;

import com.example.howtoquery.model.User;
import com.example.howtoquery.repository.UserRepository;
import com.example.howtoquery.specefication.UserSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service("userServiceV1")
public class UserServiceV1_SpecificationExample implements UserService {

    public final UserRepository userRepository;

    public UserServiceV1_SpecificationExample(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Page<User> searchUsers(
            String name,
            Integer age,
            Date createdFrom,
            Date createdTo,
            Pageable pageable) {

         Specification<User> specification = Specification
                 .where(UserSpecification.nameLike(name))
                 .and(UserSpecification.ageGreaterThanOrEqualTo(age))
                 .and(UserSpecification.createdBetween(createdFrom, createdTo))
                 .and(UserSpecification.orderByCreatedAt("DESC"));

        return userRepository.findAll(specification, pageable);
    }

    public User searchUser(Long id) {
        return userRepository.getById(id);
    }
}

package com.boldbit.bitharvester.Services;

import org.springframework.stereotype.Service;

import com.boldbit.bitharvester.Models.User;

@Service
public interface UserService {
    User createUser(User user);
    User getByEmail(String email);
    User getById(String id);
    void updateUserById(String id, User user);
}

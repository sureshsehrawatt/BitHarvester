package com.boldbit.bitharvester.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boldbit.bitharvester.Models.User;
import com.boldbit.bitharvester.Repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }
    
    @Override
    public User getByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

}

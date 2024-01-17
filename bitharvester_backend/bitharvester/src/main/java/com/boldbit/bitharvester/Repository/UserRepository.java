package com.boldbit.bitharvester.Repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.boldbit.bitharvester.Models.User;

public interface UserRepository extends MongoRepository<User, String>{
    Optional<User> findByEmail(String email);    
}

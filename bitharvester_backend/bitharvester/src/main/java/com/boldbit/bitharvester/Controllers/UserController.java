package com.boldbit.bitharvester.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.boldbit.bitharvester.Models.User;
import com.boldbit.bitharvester.Services.UserService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("api/user/signup")
    public ResponseEntity<?> createUser(@RequestBody User user) {
        try {
            userService.createUser(user);
            return ResponseEntity.ok().body("User added successfully");
        } catch (DuplicateKeyException e) {
            return new ResponseEntity<>("Email already exists", HttpStatus.CONFLICT);
        } catch (Exception e) {
            return new ResponseEntity<>("Error in signup", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("api/user/signin")
    public ResponseEntity<?> signIn(@RequestBody User userBody) {
        try {
            User user = userService.getByEmail(userBody.getEmail());
            if (user != null && userBody.getPassword().equals(user.getPassword())) {
                return ResponseEntity.ok().body("SignIn Successfully");
            } else if(user == null) {
                return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error in signIn", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("Incorrect password", HttpStatus.BAD_REQUEST);
    }
    
}

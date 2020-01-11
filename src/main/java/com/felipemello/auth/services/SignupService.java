package com.felipemello.auth.services;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.felipemello.auth.models.User;
import com.felipemello.auth.repositories.UserRepository;


@Service
public class SignupService {

    private UserRepository userRepository;

    public SignupService(UserRepository userRepository) {
	this.userRepository = userRepository;
    }

    public ResponseEntity<String> signUpUser(User model) {
	Optional<User> user = userRepository.findByEmailOrNickName(model.getEmail(), model.getNickName());

	if (user.isPresent()) {
	    return new ResponseEntity<>("User already exists", HttpStatus.OK);
	}
	userRepository.save(model);
	return new ResponseEntity<>("Signup completed", HttpStatus.ACCEPTED);

    }

}
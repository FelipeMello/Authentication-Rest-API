package com.felipemello.auth.services;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.felipemello.auth.models.CredentialModel;
import com.felipemello.auth.models.SignUpModel;
import com.felipemello.auth.models.User;
import com.felipemello.auth.repositories.UserRepository;

@Service
public class AuthenticationService {

    private UserRepository userRepository;

    public AuthenticationService(UserRepository userRepository) {
	this.userRepository = userRepository;
    }

    public ResponseEntity<User> deleteAccount(@Valid CredentialModel model) {
	Optional<User> user = findUser(model);
	if (!user.isPresent()) {
	    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	userRepository.delete(user.get());
	return new ResponseEntity<>(user.get(), HttpStatus.ACCEPTED);
    }

    public Optional<User> findUser(CredentialModel model) {

	if (null != model.getPassword()) {
	    if (null != model.getEmail()) {
		return userRepository.findByEmailAndPassword(model.getEmail(), model.getPassword());
	    }
	    if (null != model.getUsername()) {
		return userRepository.findByUsernameAndPassword(model.getUsername(), model.getPassword());
	    }
	}
	return Optional.empty();
    }

    public ResponseEntity<User> signIn(CredentialModel model) {
	Optional<User> user = findUser(model);
	if (!user.isPresent()) {
	    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	return new ResponseEntity<>(user.get(), HttpStatus.ACCEPTED);
    }

    public ResponseEntity<String> signUpUser(SignUpModel model) {
	if (userRepository.findByEmailOrUsername(model.getEmail(), model.getUsername()).isPresent()) {
	    return new ResponseEntity<>("User already exists", HttpStatus.BAD_REQUEST);
	}
	userRepository.save(User.builder().email(model.getEmail()).username(model.getUsername())
		.password(model.getPassword()).build());
	return new ResponseEntity<>("Signup completed", HttpStatus.CREATED);
    }

    public ResponseEntity<User> getUserByEmail(@Valid String email) {
	Optional<User> user = userRepository.findByEmail(email);
	if (!user.isPresent()) {
	    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	return new ResponseEntity<>(user.get(), HttpStatus.OK);
    }
}
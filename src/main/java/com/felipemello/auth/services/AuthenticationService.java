package com.felipemello.auth.services;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.felipemello.auth.models.SignInModel;
import com.felipemello.auth.models.SignUpModel;
import com.felipemello.auth.models.User;
import com.felipemello.auth.repositories.UserRepository;

@Service
public class AuthenticationService {

    private UserRepository userRepository;

    public AuthenticationService(UserRepository userRepository) {
	this.userRepository = userRepository;
    }

    public ResponseEntity<String> signUpUser(SignUpModel model) {
	Optional<User> user = userRepository.findByEmailOrUsername(model.getEmail(), model.getUsername());

	if (user.isPresent()) {
	    return new ResponseEntity<>("User already exists", HttpStatus.OK);
	}
	userRepository.save(User.builder().email(model.getEmail()).username(model.getUsername())
		.password(model.getPassword()).build());
	return new ResponseEntity<>("Signup completed", HttpStatus.ACCEPTED);
    }
    
    public ResponseEntity<User> signIn(SignInModel model) {
	Optional<User> user = findUser(model);
	if(!user.isPresent()) {
	    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	return new ResponseEntity<>(user.get(), HttpStatus.ACCEPTED);
    }
    
    public Optional<User> findUser(SignInModel model){
	if(null != model.getPassword()) {
	    if(null != model.getEmail()) {
		    return userRepository.findByEmailAndPassword(model.getEmail(), model.getPassword());
	    }
	    if(null != model.getUsername()) {
		return userRepository.findByUsernameAndPassword(model.getUsername(), model.getPassword());
	    }
	}
	return Optional.empty();
	
    }
    
    
    

}
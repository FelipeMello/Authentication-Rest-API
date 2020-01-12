package com.felipemello.auth.controllers;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.felipemello.auth.models.CredentialModel;
import com.felipemello.auth.models.SignUpModel;
import com.felipemello.auth.models.User;
import com.felipemello.auth.services.AuthenticationService;

@RestController
@RequestMapping("/api/v1")
public class AuthenticationController {

    private AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService signupService) {
	this.authenticationService = signupService;
    }

    @DeleteMapping("/deleteaccount")
    public ResponseEntity<User> deleteAccount(@Valid @RequestBody CredentialModel model) {
	return authenticationService.deleteAccount(model);
    }

    @PostMapping("/signin")
    public ResponseEntity<User> signIn(@Valid @RequestBody CredentialModel model) {
	return authenticationService.signIn(model);
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@Valid @RequestBody SignUpModel model) {
	return authenticationService.signUpUser(model);
    }
    
    @GetMapping("/user/email{email}")
    public ResponseEntity<User> getUserByEmail(@Valid @RequestParam String email) {
	return authenticationService.getUserByEmail(email);
    }
    
    

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
	Map<String, String> errors = new HashMap<>();
	ex.getBindingResult().getAllErrors().forEach((error) -> {
	    String fieldName = ((FieldError) error).getField();
	    String errorMessage = error.getDefaultMessage();
	    errors.put(fieldName, errorMessage);
	});
	return errors;
    }
}

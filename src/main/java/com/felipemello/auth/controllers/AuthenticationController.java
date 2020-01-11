package com.felipemello.auth.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.felipemello.auth.models.User;
import com.felipemello.auth.services.SignupService;

@RestController
@RequestMapping("/api/v1")
public class AuthenticationController {

    private SignupService signupService;

    public AuthenticationController(SignupService signupService) {
	this.signupService = signupService;
    }

    @PostMapping("/singup")
    public ResponseEntity<String> signUp(@RequestBody User model) {
	return signupService.signUpUser(model);
    }

}


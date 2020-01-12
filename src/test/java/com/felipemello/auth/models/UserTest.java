package com.felipemello.auth.models;

import org.assertj.core.api.Assertions;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class UserTest {

    private User model;
    private static ValidatorFactory validatorFactory;
    private static Validator validator;

    @BeforeClass
    public static void createValidator() {
	validatorFactory = Validation.buildDefaultValidatorFactory();
	validator = validatorFactory.getValidator();
    }
    
    @AfterClass
    public static void close() {
	validatorFactory.close();
    }
    
    @Before
    public void setUp() {
	model = User.builder().email("test@gmail.com").username("test").password("password").userType(UserType.MANAGER)
		.id("5a9427648b0beebeb69579cc").build();
    }


    @Test
    public void testBuilder() {
	assertThat(model.getEmail(), is("test@gmail.com"));
	assertThat(model.getUsername(), is("test"));
	assertThat(model.getPassword(), is("password"));
	assertThat(model.getUserType(), is(UserType.MANAGER));
	assertThat(model.getId().toString(), is("5a9427648b0beebeb69579cc"));
    }
    
    @Test
    public void testViolation() {
	model = User.builder().build();
	Set<ConstraintViolation<User>> violations = validator.validate(model);
	assertEquals(5, violations.size());
	
	Assertions.assertThat(violations).extracting("message").containsOnly("Email is mandatory", "User type is mandatory",
		"Password is mandatory", "Username is mandatory", "Id is mandatory");
    }

}

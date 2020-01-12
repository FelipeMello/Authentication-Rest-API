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

public class SignUpModelTest {

    private SignUpModel model;
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
	model = SignUpModel.builder().email("test@gmail.com").username("test").password("password")
		.userType(UserType.STAFF).build();
    }

    @Test
    public void testBuilder() {
	assertThat(model.getEmail(), is("test@gmail.com"));
	assertThat(model.getUsername(), is("test"));
	assertThat(model.getPassword(), is("password"));
	assertThat(model.getUserType(), is(UserType.STAFF));
    }

    @Test
    public void testViolation() {
	model = SignUpModel.builder().build();
	Set<ConstraintViolation<SignUpModel>> violations = validator.validate(model);
	assertThat(4, is(violations.size()));
	Assertions.assertThat(violations).extracting("message").containsOnly("Email is mandatory", "User type is mandatory",
		"Password is mandatory", "Username is mandatory");
    }

}

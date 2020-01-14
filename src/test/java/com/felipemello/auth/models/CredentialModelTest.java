package com.felipemello.auth.models;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.assertj.core.api.Assertions;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class CredentialModelTest {

    private CredentialModel model;
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
	model = CredentialModel.builder().email("test@gmail.com").username("test").password("password")
		.build();
    }

    @Test
    public void testBuilder() {
	assertThat(model.getEmail(), is("test@gmail.com"));
	assertThat(model.getUsername(), is("test"));
	assertThat(model.getPassword(), is("password"));
    }

    @Test
    public void testViolation() {
	// given
	model = CredentialModel.builder().email("test@gmail.com").username("test").build();
	// when:
	Set<ConstraintViolation<CredentialModel>> violations = validator.validate(model);

	// then:
	assertEquals(1, violations.size());
	assertThat(model.getEmail(), is("test@gmail.com"));
	assertThat(model.getUsername(), is("test"));
	Assertions.assertThat(violations).extracting("message").containsOnly("Password is mandatory");
	
    }
    
    
}

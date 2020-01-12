package com.felipemello.auth.models;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class UserTypeTest {
    
    @Test
    public void testValue() {
	UserType userType = UserType.MANAGER;
	assertThat(UserType.MANAGER, is(userType));
	userType = UserType.STAFF;
	assertThat(UserType.STAFF, is(userType));
    }
}

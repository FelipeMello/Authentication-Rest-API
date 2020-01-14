//package com.felipemello.auth.repositories;
//
//
//
//import static org.junit.Assert.*;
//
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.mongodb.core.MongoTemplate;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import com.felipemello.auth.AuthenticationApplication;
//import com.felipemello.auth.models.User;
//import com.felipemello.auth.models.UserType;
//
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = AuthenticationApplication.class)
//public class UserRepositoryTest {
//
//    @Autowired
//    private MongoTemplate mongoTemplate;
//    
//    @Autowired
//    private UserRepository userRepository;
//    
//
//    
//
//    @Before
//    public void setup() throws Exception {
//        mongoTemplate.dropCollection(User.class);
//    }
//    
//    @After
//    public void tearDown() throws Exception {
//        mongoTemplate.dropCollection(User.class);
//    }
//    
//    
//    private static User model;
//
//    @Before
//    public void setUp() {
//	model = User.builder().email("test@gmail.com").username("test").password("password").userType(UserType.MANAGER)
//	.id("5a9427648b0beebeb69579cc").build();
//    }
//    
//    @Test
//    public void testSaveUser() {
//	assertEquals(model, userRepository.save(model));
//    }
//    
//    
//
//}

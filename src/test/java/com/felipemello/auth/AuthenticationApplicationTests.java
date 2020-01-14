package com.felipemello.auth;

import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.felipemello.auth.consumer.Consumer;
import com.felipemello.auth.producer.Producer;


@SpringBootTest
public class AuthenticationApplicationTests {
    
    @Autowired
    private Producer producer;

    @Autowired
    private Consumer consumer;
    
    
    @Test
    void contextLoads() {
	 assertTrue(true);
    }


    @Test
    public void testConsumer() throws InterruptedException {
	producer.send("Hello Spring World I am learning JMS ActiveMQ!");
	consumer.getLatch().await(10000, TimeUnit.MILLISECONDS);
	assertThat(consumer.getLatch().getCount()).isEqualTo(0);
    }

  
}

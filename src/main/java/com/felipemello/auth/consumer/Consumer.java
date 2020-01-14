package com.felipemello.auth.consumer;

import java.util.concurrent.CountDownLatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;

public class Consumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(Consumer.class);

    private CountDownLatch latch = new CountDownLatch(1);

    public CountDownLatch getLatch() {
	return latch;
    }

    @JmsListener(destination = "helloworld.q")
    public void consume(String message) {
	LOGGER.info("consume message='{}'", message);
	latch.countDown();
    }
}

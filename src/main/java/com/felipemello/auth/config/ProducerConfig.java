package com.felipemello.auth.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.core.JmsTemplate;

import com.felipemello.auth.producer.Producer;

@Configuration
public class ProducerConfig {

    @Value("${activemq.broker-url}")
    private String brokerUrl;

    @Bean
    public ActiveMQConnectionFactory senderActiveMQConnectionFactory() {
	ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory();
	activeMQConnectionFactory.setBrokerURL(brokerUrl);
	return activeMQConnectionFactory;
    }

    @Bean
    public CachingConnectionFactory cachingConnectionFactory() {
	return new CachingConnectionFactory(senderActiveMQConnectionFactory());
    }

    @Bean
    public JmsTemplate jmsTemplate() {
	return new JmsTemplate(cachingConnectionFactory());
    }

    @Bean
    public Producer producer() {
	return new Producer();
    }
}
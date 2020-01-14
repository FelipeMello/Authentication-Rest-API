package com.felipemello.auth.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;

import com.felipemello.auth.consumer.Consumer;

@Configuration
@EnableJms
public class ConsumerConfig {

    @Value("${activemq.broker-url}")
    private String brokerUrl;

    @Bean
    public ActiveMQConnectionFactory receiverActiveMQConnectionFactory() {
	ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory();
	activeMQConnectionFactory.setBrokerURL(brokerUrl);

	return activeMQConnectionFactory;
    }

    @Bean
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory() {
	DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
	factory.setConnectionFactory(receiverActiveMQConnectionFactory());

	return factory;
    }

    @Bean
    public Consumer consumer() {
	return new Consumer();
    }
}

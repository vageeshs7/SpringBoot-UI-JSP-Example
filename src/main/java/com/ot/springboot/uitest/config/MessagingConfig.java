package com.ot.springboot.uitest.config;

import org.apache.qpid.jms.JmsConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.ConnectionFactory;

@Configuration
@EnableJms
public class MessagingConfig {
    @Value("jms.provider.url")
    private String jmsProviderURL;

    @Value("jms.provider.username")
    private String jmsProviderUsername;

    @Value("jms.provider.password")
    private String jmsProviderPassword;

    @Value("jms.destination.todo-save")
    private String todoSaveQueueName;

    public String getTodoSaveQueueName() {
        return todoSaveQueueName;
    }

    @Bean
    public ConnectionFactory messagingConnectionFactory(){
        ConnectionFactory connectionFactory = new JmsConnectionFactory(jmsProviderUsername, jmsProviderPassword, jmsProviderURL);
        return connectionFactory;
    }

    @Bean
    public CachingConnectionFactory cachingConnectionFactory() {
        CachingConnectionFactory cachingConnectionFactory =
                new CachingConnectionFactory(messagingConnectionFactory());
        cachingConnectionFactory.setSessionCacheSize(10);

        return cachingConnectionFactory;
    }

    @Bean
    public JmsTemplate jmsTemplate() {
        JmsTemplate jmsTemplate =
                new JmsTemplate(cachingConnectionFactory());
        //jmsTemplate.setDefaultDestination(orderDestination());
        jmsTemplate.setReceiveTimeout(5000);

        return jmsTemplate;
    }
}

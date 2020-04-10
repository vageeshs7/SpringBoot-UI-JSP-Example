package com.ot.springboot.uitest.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.ConnectionFactory;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.IOException;
import java.util.Properties;

@Configuration
public class MessagingConfig {
    @Value("${jms.provider.url}")
    private String jmsProviderURL;

    @Value("${jms.provider.username}")
    private String jmsProviderUsername;

    @Value("${jms.provider.password}")
    private String jmsProviderPassword;

    @Value("${jms.destination.todo-save}")
    private String todoSaveQueueName;

    @Value("${jms.initial.factory}")
    private String initialConnectionFactory;

    public String getTodoSaveQueueName() {
        return todoSaveQueueName;
    }

    private Logger logger = LogManager.getLogger(MessagingConfig.class);

    @Bean
    public ConnectionFactory messagingConnectionFactory() throws NamingException, IOException {
        logger.info("Creating ConnectionFactory with " + jmsProviderURL + ", " + jmsProviderUsername + ", " + ConnectionFactory.class);
        Properties properties = new Properties();
        properties.load(this.getClass().getResourceAsStream("messaging.properties"));
        Context context = new InitialContext(properties);

        ConnectionFactory connectionFactory
                = (ConnectionFactory) context.lookup("qpidConnectionFactory");
        return connectionFactory;
    }

    @Bean
    public CachingConnectionFactory cachingConnectionFactory() throws IOException, NamingException {
        logger.info("Creating caching con fac " + CachingConnectionFactory.class);
        CachingConnectionFactory cachingConnectionFactory =
                new CachingConnectionFactory(messagingConnectionFactory());
        cachingConnectionFactory.setSessionCacheSize(10);

        return cachingConnectionFactory;
    }

    @Bean
    public JmsTemplate jmsTemplate() throws IOException, NamingException {
        logger.info("Creating jms template : " + JmsTemplate.class);
        JmsTemplate jmsTemplate =
                new JmsTemplate(cachingConnectionFactory());
        //jmsTemplate.setDefaultDestination(orderDestination());
        jmsTemplate.setReceiveTimeout(5000);

        return jmsTemplate;
    }
}

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
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

@Configuration
public class MessagingConfig {
    @Value("${jms.destination.todo-save}")
    private String todoSaveQueueName;

    public String getTodoSaveQueueName() {
        return todoSaveQueueName;
    }

    private Logger logger = LogManager.getLogger(MessagingConfig.class);

    @Bean
    public ConnectionFactory messagingConnectionFactory() throws NamingException, IOException {
        logger.info("Creating ConnectionFactory with" + ConnectionFactory.class);
        Properties properties = new Properties();
        File file = new File(this.getClass().getResource("messaging.properties").getPath());
        logger.info("Reading from file : " + file.getAbsolutePath());
        properties.load(new FileInputStream(file));
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
    public JmsTemplate jmsTemplate()  {
        logger.info("Creating jms template : " + JmsTemplate.class);
        JmsTemplate jmsTemplate =
                null;
        try {
            jmsTemplate = new JmsTemplate(cachingConnectionFactory());
            jmsTemplate.setReceiveTimeout(5000);
        } catch (Exception e) {
            logger.error(e);
        }
        //jmsTemplate.setDefaultDestination(orderDestination());


        return jmsTemplate;
    }
}

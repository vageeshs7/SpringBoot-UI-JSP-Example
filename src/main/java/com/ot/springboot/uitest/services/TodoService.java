package com.ot.springboot.uitest.services;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Queue;
import org.apache.qpid.jms.JmsConnectionFactory;
import org.springframework.stereotype.Service;

import com.ot.springboot.uitest.dom.Todo;


@Service
public class TodoService {
    private static List<Todo> todos = new ArrayList<Todo>();
    private static Integer todoCount = 3;

    static {
        todos.add(new Todo(1, "admin", "Learn Spring MVC", new Date(),false));
        todos.add(new Todo(2, "admin", "Learn Struts", new Date(), false));
        todos.add(new Todo(3, "admin", "Learn Hibernate", new Date(),false));
    }

    public List<Todo> retrieveTodos(String user) {
        List<Todo> filteredTodos = new ArrayList<Todo>();
        for (Todo todo : todos) {
            if (todo.getUser().equals(user)) {
                filteredTodos.add(todo);
            }
        }
        return filteredTodos;
    }

    public void addNewTodo(Todo todo, String user){
        todo.setUser(user);
        synchronized(todoCount){
            todoCount++;
            todo.setId(todoCount);
        }
        todos.add(todo);

        //Send message to back end
        String jmsHost = "amqps://hk-np1.fs-solace.dev.net:10130";
        String jmsUsername = "app-test-user";
        String jmsPassword = "app-test-user";
        System.out.printf("Publisher is connecting to JMS messaging at %s...%n", jmsHost);

        // Programmatically create the connection factory using default settings
        ConnectionFactory connectionFactory = new JmsConnectionFactory(solaceUsername, solacePassword, solaceHost);

        // Create connection to the Solace messaging
        Connection connection = connectionFactory.createConnection();

        // Create a non-transacted, client ACK session.
        Session session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);

        System.out.printf("Connected with username '%s'.%n", solaceUsername);

        // Create the queue programmatically and the corresponding messaging resource
        Queue queue = session.createQueue("q-vas-test-conn");
    }

    public void markTodo(int id, boolean isDone){
        Todo todo = todos.get(id-1);
        todo.setDone(isDone);
    }
}
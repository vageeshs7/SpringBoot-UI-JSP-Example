package com.ot.springboot.uitest.services;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ot.springboot.uitest.config.MessagingConfig;
import com.ot.springboot.uitest.dom.Todo;
import org.apache.qpid.jms.message.JmsTextMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import javax.jms.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class TodoService {
    private static List<Todo> todos = new ArrayList<Todo>();
    private static Integer todoCount = 3;
    ObjectMapper jsonMapper = new ObjectMapper();

    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    private MessagingConfig messagingConfig;

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

        jmsTemplate.send(messagingConfig.getTodoSaveQueueName(), new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                TextMessage message = null;
                try {
                    String jsonText = jsonMapper.writeValueAsString(todo);
                    message = session.createTextMessage(jsonText);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
                return message;
            }
        });


    }

    public void markTodo(int id, boolean isDone){
        Todo todo = todos.get(id-1);
        todo.setDone(isDone);
    }
}
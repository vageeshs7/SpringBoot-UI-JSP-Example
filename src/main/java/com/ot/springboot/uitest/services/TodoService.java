package com.ot.springboot.uitest.services;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.ot.springboot.uitest.config.MessagingConfig;
import com.ot.springboot.uitest.dom.Todo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;


@Service
public class TodoService {
    private static List<Todo> todos = new ArrayList<Todo>();
    private static Integer todoCount = 3;
    ObjectMapper jsonMapper = new ObjectMapper();
    private Logger logger = LogManager.getLogger(MessagingConfig.class);

    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    private MessagingConfig messagingConfig;

    @Autowired
    RestTemplate restTemplate;

    @Value("${todolist.backend.url}")
    private String  backendUrl;

    /*static {
        todos.add(new Todo(1, "admin", "Learn Spring MVC", new Date(),false));
        todos.add(new Todo(2, "admin", "Learn Struts", new Date(), false));
        todos.add(new Todo(3, "admin", "Learn Hibernate", new Date(),false));
    }*/

    public List<Todo> retrieveTodos(String user) {
        List<Todo> filteredTodos = new ArrayList<Todo>();
        for (Todo todo : todos) {
            if (todo.getUser().equals(user)) {
                filteredTodos.add(todo);
            }
        }
        if(filteredTodos.size() == 0){
            filteredTodos = this.getTodosFromBackEnd(user);
            todos.addAll(filteredTodos);
        }

        return filteredTodos;
    }

    public List<Todo> getTodosFromBackEnd(String user) {
        List<Todo> todoList = new ArrayList<Todo>();
        String url = backendUrl.replace("#userid#", user);
        Todo[] todoArray = restTemplate.getForObject(url, Todo[].class);

        if(todoArray.length > 0){
            for(Todo todo: todoArray){
                todoList.add(todo);
            }
        }
        return todoList;
    }

    public void addNewTodo(Todo todo, String user) throws Exception{
        todo.setUser(user);
        synchronized(todoCount){
            todoCount++;
            todo.setId(todoCount);
        }
        todos.add(todo);

        jmsTemplate.convertAndSend(messagingConfig.getTodoSaveQueueName(), todo);

    }

    public void markTodo(int id, boolean isDone){
        Todo todo = todos.get(id-1);
        todo.setDone(isDone);
    }
}
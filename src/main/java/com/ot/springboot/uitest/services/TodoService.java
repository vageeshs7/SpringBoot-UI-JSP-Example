package com.ot.springboot.uitest.services;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    }

    public void markTodo(int id, boolean isDone){
        Todo todo = todos.get(id-1);
        todo.setDone(isDone);
    }
}
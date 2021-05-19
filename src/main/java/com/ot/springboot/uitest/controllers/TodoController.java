package com.ot.springboot.uitest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.ot.springboot.uitest.dom.Todo;
import com.ot.springboot.uitest.services.TodoService;

@Controller
@SessionAttributes("name")
public class TodoController {
    @Autowired
    TodoService service;

    @RequestMapping(value = "/list-todos", method = RequestMethod.GET)
    public String showTodos(final ModelMap model) {
        final String name = (String) model.get("name");
        model.put("todos", service.retrieveTodos(name));
        return "list-todos";
    }

    @RequestMapping(value = "/add-new-todo", method = RequestMethod.GET)
    public String addNewTodo(final ModelMap model) {
        return "add-new-todo";
    }

    @RequestMapping(value = "/add-new-todo", method = RequestMethod.POST)
    public String submitNewTodo(final ModelMap model, @RequestParam final String desc,
            @RequestParam final String targetDateStr) throws ParseException {
        System.out.println("targetDateStr=" + targetDateStr);
    	final String name = (String) model.get("name");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        final Todo todo = new Todo(0, name, desc, dateFormat.parse(targetDateStr), false);
        try {
            service.addNewTodo(todo, name);
        } catch (Exception e) {
            e.printStackTrace();
        }

        model.put("todos", service.retrieveTodos(name));
        return "list-todos";
    }

    @RequestMapping(value = "/list-todos", method = RequestMethod.PUT)
    public String markTodo(final ModelMap model, @RequestParam final String id, @RequestParam final String isDone)  {
        final String name = (String) model.get("name");
       
        service.markTodo(Integer.parseInt(id), Boolean.parseBoolean(isDone));

        model.put("todos", service.retrieveTodos(name));
        return "list-todos";
    }
}

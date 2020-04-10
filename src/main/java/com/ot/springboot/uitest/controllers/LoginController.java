package com.ot.springboot.uitest.controllers;

import com.ot.springboot.uitest.config.MessagingConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletRequest;

import com.ot.springboot.uitest.services.LoginService;

@Controller
@SessionAttributes("name")
public class LoginController {
	@Autowired
    LoginService service;

    private Logger logger = LogManager.getLogger(LoginController.class);
	
	@RequestMapping(value="/", method = RequestMethod.GET)
    public String showLoginPage(ModelMap model, ServletRequest request){
        logger.info("Client=" + request.getLocalAddr());
        return "login";
    }
	
	@RequestMapping(value="/login", method = RequestMethod.POST)
    public String showWelcomePage(ModelMap model, @RequestParam String name, @RequestParam String password){

        boolean isValidUser = service.validateUser(name, password);

        if (!isValidUser) {
            model.put("errorMessage", "Invalid Credentials");
            return "login";
        }

        model.put("name", name);
        model.put("password", password);

        return "welcome";
    }
}

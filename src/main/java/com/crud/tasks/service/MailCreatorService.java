package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.ArrayList;
import java.util.List;

@Service
public class MailCreatorService {

    @Autowired
    private AdminConfig adminConfig;
    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;

    public String buildTrelloCardEmail(String message){

        List<String> functionality = new ArrayList<>();
        functionality.add("You can manage your tasks");
        functionality.add("Provides connection with Trello accounts");
        functionality.add("Application allows sending tasks to Trello");

        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("tasks_url", "https://pisiorek.github.io/");
        context.setVariable("button", "Visit website");
        context.setVariable("admin_name", adminConfig);
        context.setVariable("goodbye_message","Thank you and see you again !");
        context.setVariable("preview_message","Welcome in our service");
        context.setVariable("company_name", adminConfig.getCompanyName());
        context.setVariable("company_email", adminConfig.getCompanyEmail());
        context.setVariable("company_phone", adminConfig.getCompanyPhone());
        context.setVariable("show_button", true);
        context.setVariable("if_friend", false);
        context.setVariable("application_functionality", functionality);
        return templateEngine.process("mail/created-trello-card-mail", context);
    }
}

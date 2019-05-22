package com.crud.tasks.scheduler;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.Mail;
import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.service.SimpleEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class EmailScheduler {

    public static final String SUBJECT = "TASKS: Once a day email";
    private String taskOrTasks = " task";


    @Autowired
    private SimpleEmailService simpleEmailService;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private AdminConfig adminConfig;

    public String getTaskOrTasks() {
        return taskOrTasks;
    }

    @Scheduled(cron = "0 0 10 * * *")
    //@Scheduled(fixedDelay = 10000)

    public void sendInformationEmail(){

        long size = taskRepository.count();
        if(size > 1){
            taskOrTasks = " tasks";
        }

        simpleEmailService.send(new Mail(
                adminConfig.getAdminMail(),
                SUBJECT,
                "Currently in database you got " + size + taskOrTasks
        ));
    }
}

package com.crud.tasks.scheduler;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.Mail;
import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.service.SimpleEmailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class EmailSchedulerTestSuite {
    @InjectMocks
    private EmailScheduler emailScheduler;

    @Mock
    private SimpleEmailService simpleEmailService;
    @Mock
    private TaskRepository taskRepository;
    @Mock
    private AdminConfig adminConfig;
    @Mock
    private JavaMailSender javaMailSender;

    @Test
    public void testSchedulerShouldSendEmail(){

        Mail eMail = new Mail("test@test.com", "Subject", "message", "darek@kodilla.pl");

        when(taskRepository.count()).thenReturn(1L);
        doNothing().when(simpleEmailService).send(eMail); // dla metod void stsoujemy doNothing()

        //When
        emailScheduler.sendInformationEmail();

        //Then
        assertEquals(" task", emailScheduler.getTaskOrTasks());
    }

}

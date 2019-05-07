package com.crud.tasks.service;

import com.crud.tasks.domain.Mail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class SimpleEmailServiceTest {

    @InjectMocks
    private SimpleEmailService simpleEmailService;

    @Mock
    private JavaMailSender javaMailSender;

    @Test
    public void shouldSendEmail(){
        //Given
        Mail mail = new Mail("test@test.com", "Test subject", "Test message");

        /**
         * ArgumentCaptor przechwytuje (obiekt) wiadomość zwróconą i sformatowaną przez SimpleMailMessage
         * pozwala na ograniczenie w testach dublowania kodu i wykorzystanie już istniejącego
         */
        ArgumentCaptor<SimpleMailMessage> mailArgs = ArgumentCaptor.forClass(SimpleMailMessage.class);

        //When
        simpleEmailService.send(mail);

        //Then
        verify(javaMailSender, times(1)).send(mailArgs.capture());
    }

    @Test
    public void shouldSendEmailTest() {
        //Given
        Mail eMail = new Mail("test@test.com", "Subject", "message", "darek@kodilla.pl");

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(eMail.getMailTo());
        message.setSubject(eMail.getSubject());
        message.setText(eMail.getMessage());
        message.setCc(eMail.getToCc());

        //When
        simpleEmailService.send(eMail);

        //Then
        verify(javaMailSender, times(1)).send(message);
    }
}
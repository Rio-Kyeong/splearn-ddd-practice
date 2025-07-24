package ddd.splearn.adapter.integration;

import ddd.splearn.application.required.EmailSender;
import ddd.splearn.domain.shared.Email;
import org.springframework.stereotype.Component;

@Component
public class DummyEmailSender implements EmailSender {
    @Override
    public void send(Email email, String subject, String body) {
        System.out.println("DummyEmailSender Sending email: " + email);
    }
}

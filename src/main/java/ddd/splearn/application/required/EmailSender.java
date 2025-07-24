package ddd.splearn.application.required;

import ddd.splearn.domain.shared.Email;

/**
 * 이메일 발송 기능 제공
 */
public interface EmailSender {
    void send(Email email, String subject, String body);
}

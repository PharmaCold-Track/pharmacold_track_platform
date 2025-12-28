package com.pharmacoldtrack.platform.shared.infrastructure.external.smtp;

import com.pharmacoldtrack.platform.shared.application.external.ExternalNotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@Primary
@RequiredArgsConstructor
public class GmailEmailService implements ExternalNotificationService {

    private final JavaMailSender mailSender;

    @Override
    public void sendAlert(String email, String subject, String message) {
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom("elmeraugustoriva@gmail.com");
            mailMessage.setTo(email);
            mailMessage.setSubject(subject);
            mailMessage.setText(message);

            mailSender.send(mailMessage);
            log.info("Email sent successfully to {}", email);
        } catch (Exception e) {
            log.error("Failed to send email: {}", e.getMessage());
        }
    }
}
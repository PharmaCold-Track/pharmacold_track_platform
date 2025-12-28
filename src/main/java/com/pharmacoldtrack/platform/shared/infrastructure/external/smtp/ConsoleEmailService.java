package com.pharmacoldtrack.platform.shared.infrastructure.external.smtp;

import com.pharmacoldtrack.platform.shared.application.external.ExternalNotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ConsoleEmailService implements ExternalNotificationService {
    @Override
    public void sendAlert(String email, String subject, String message) {
        log.info("================ EMAIL SENT ================");
        log.info("TO: {}", email);
        log.info("SUBJECT: {}", subject);
        log.info("BODY: {}", message);
        log.info("============================================");
    }
}
package com.pharmacoldtrack.platform.shared.application.external;

public interface ExternalNotificationService {
    void sendAlert(String email, String subject, String message);
}
package com.seatwatcher.seatwatcher.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendSeatAvailableEmail(
            String to,
            String courseCode,
            String section,
            int seats) {

        // Temporary debugging line
        System.out.println("Mail user: " + System.getenv("MAIL_USERNAME"));
        String mailPassword = System.getenv("MAIL_PASSWORD");
        System.out.println(
                "MAIL_PASSWORD length = "
                        + (mailPassword == null ? "null" : mailPassword.length())
        );
        System.out.println("MAIL_USERNAME = "
                + System.getenv("MAIL_USERNAME"));

        System.out.println("DB_PASSWORD exists = "
                + (System.getenv("DB_PASSWORD") != null));

        System.out.println("MAIL_PASSWORD exists = "
                + (System.getenv("MAIL_PASSWORD") != null));

        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(to);
        message.setSubject("Seat Available - " + courseCode);

        message.setText(
                courseCode
                        + " section "
                        + section
                        + " now has "
                        + seats
                        + " available seats."
        );

        mailSender.send(message);
    }
}

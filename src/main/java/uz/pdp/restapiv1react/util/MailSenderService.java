package uz.pdp.restapiv1react.util;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailSenderService {
    @Value("${sender}")
    private String sender;
    private final JavaMailSender mailSender;

    public synchronized void send(final String message, final String... to){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(to);
        mailMessage.setFrom(sender);
        mailMessage.setText("Restore your password : "+message);
        mailMessage.setSubject("Reset password request");
        mailSender.send(mailMessage);
    }
}

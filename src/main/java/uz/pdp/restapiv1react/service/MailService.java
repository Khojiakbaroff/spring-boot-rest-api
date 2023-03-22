package uz.pdp.restapiv1react.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import uz.pdp.restapiv1react.repository.EmployeeRepository;
import uz.pdp.restapiv1react.service.util.PasswordGenerator;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MailService {
    @Value("${sender}")
    @NonFinal
    String sender;
    JavaMailSender mailSender;
    EmployeeRepository repository;

    public boolean checkEmailIfExist(String email) {
        return this.repository.findByEmail(email).isPresent();
    }

    private synchronized void sendRecoveryPassword(final String to) {
        String pas = PasswordGenerator.getRandomNumberString();
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(to);
        mailMessage.setFrom(sender);
        mailMessage.setText("Password for recovery : " + pas);
        mailMessage.setSubject("Reset password request");
        mailSender.send(mailMessage);
    }
}

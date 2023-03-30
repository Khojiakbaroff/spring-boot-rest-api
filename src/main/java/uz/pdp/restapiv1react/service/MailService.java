package uz.pdp.restapiv1react.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import uz.pdp.restapiv1react.customExcaptions.EmailServiceException;
import uz.pdp.restapiv1react.repository.EmployeeRepository;
import uz.pdp.restapiv1react.service.util.PasswordGenerator;

import static uz.pdp.restapiv1react.error_messages.Constants.ERROR_WITH_SENDING_EMAIL;

@Slf4j
@Service
@RequiredArgsConstructor
public class MailService {

    @Value("${sender}")
    private String sender;

    private final JavaMailSender mailSender;

    private final EmployeeRepository repository;

    public boolean checkEmailIfExist(String email) {
        return this.repository.findByEmail(email).isPresent();
    }

    private void sendRecoveryPassword(final String to) throws EmailServiceException {
        String pas = PasswordGenerator.getRandomNumberString();
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(to);
            mailMessage.setFrom(sender);
            mailMessage.setText("Password for recovery : " + pas);
            mailMessage.setSubject("Reset password request");
            mailSender.send(mailMessage);
        } catch (Exception e) {
            log.error(ERROR_WITH_SENDING_EMAIL + e);
            throw new EmailServiceException(e.getMessage());
        }
    }
}

package uz.pdp.restapiv1react.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.restapiv1react.util.MailSenderService;
import uz.pdp.restapiv1react.util.PasswordGenerator;

@Service
@RequiredArgsConstructor
public class MailService {
    private final MailSenderService mailSenderService;
    public void sendPasswordToEmail(final String... to){
        mailSenderService.send(PasswordGenerator.getRandomNumberString(), to);
    }
}

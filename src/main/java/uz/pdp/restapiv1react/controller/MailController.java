package uz.pdp.restapiv1react.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.pdp.restapiv1react.service.MailService;

@RestController
@RequestMapping("/api/v1/mail")
@RequiredArgsConstructor
public class MailController {

    private final MailService mailService;

    @GetMapping("/checkEmail")
    public boolean checkExistsEmail(
            @RequestParam String email
    ) {
        return this.mailService.checkEmailIfExist(email);
    }
}

package uz.pdp.restapiv1react.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;
import uz.pdp.restapiv1react.service.MailService;

@RestController
@RequestMapping("/api/v1/mail")
@CrossOrigin(origins = "http://localhost:3000")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class MailController {
    MailService mailService;

    @GetMapping("/checkEmail")
    public boolean checkExistsEmail(
            @RequestParam String email
    ){
        return this.mailService.checkEmailIfExist(email);
    }
}

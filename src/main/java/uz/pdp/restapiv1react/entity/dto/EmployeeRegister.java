package uz.pdp.restapiv1react.entity.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import uz.pdp.restapiv1react.enums.Gender;
import uz.pdp.restapiv1react.enums.Role;

import java.util.Set;

@Builder
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmployeeRegister {
    Integer id;
    @NotBlank
    String lastname;
    @NotBlank
    String firstname;
    @NotBlank
    String password;
    @NotBlank
    String email;
    Boolean status;
    Gender gender;
    Set<Role> roles;
}

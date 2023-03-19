package uz.pdp.restapiv1react.entity.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import uz.pdp.restapiv1react.enums.Role;

import java.util.Set;

@Builder
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmployeeLogin {
    @NotBlank
    String email;
    @NotBlank
    String password;
    Set<Role> roles;
}

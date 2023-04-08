package uz.pdp.restapiv1react.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;
import lombok.experimental.FieldDefaults;
import uz.pdp.restapiv1react.enums.Gender;
import uz.pdp.restapiv1react.enums.Role;
import uz.pdp.restapiv1react.enums.Status;

import java.util.Set;

@Getter
@Builder
@Setter
@Entity(name = "employee")
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    String firstname;

    String lastname;

    @Column(nullable = false, length = 30)
    String password;

    @Column(unique = true, nullable = false, length = 50)
    @Email
    String email;

    @Enumerated(EnumType.STRING)
    Status status;

    @Enumerated(EnumType.STRING)
    Gender gender;

    @Enumerated(EnumType.STRING)
    Set<Role> roles;

}


package uz.pdp.restapiv1react.entity.dto;

import uz.pdp.restapiv1react.entity.EmployeeEntity;
import uz.pdp.restapiv1react.enums.Role;
import uz.pdp.restapiv1react.enums.Status;

import java.util.HashSet;
import java.util.Set;

public class EmployeeBuilder {

    public static EmployeeEntity of(EmployeeRegister employeeRegister) {
        return EmployeeEntity
                .builder()
                .id(employeeRegister.getId())
                .firstname(employeeRegister.getFirstname())
                .lastname(employeeRegister.getLastname())
                .email(employeeRegister.getEmail())
                .gender(employeeRegister.getGender())
                .password(employeeRegister.getPassword())
                .status(Status.ACTIVE)
                .roles(setRolesIfNotExist(employeeRegister.getRoles()))
                .build();
    }


    private static Set<Role> setRolesIfNotExist(Set<Role> roles) {
        if (roles == null) {
            roles = new HashSet<>();
            roles.add(Role.USER);
        } else if (roles.isEmpty()) {
            roles.add(Role.USER);
        } else {
            return roles;
        }
        return roles;
    }

}

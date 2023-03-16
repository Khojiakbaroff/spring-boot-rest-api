package uz.pdp.restapiv1react.entity.dto;

import uz.pdp.restapiv1react.entity.EmployeeEntity;

public class EmployeeBuilder {

    public static EmployeeEntity of(EmployeeRegister employeeRegister){
        return EmployeeEntity
                .builder()
                .id(employeeRegister.getId())
                .firstname(employeeRegister.getFirstname())
                .username(employeeRegister.getUsername())
                .lastname(employeeRegister.getLastname())
                .email(employeeRegister.getEmail())
                .gender(employeeRegister.getGender())
                .password(employeeRegister.getPassword())
                .status(true)
                .roles(employeeRegister.getRoles())
                .build();
    }
}

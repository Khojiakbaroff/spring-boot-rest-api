package uz.pdp.restapiv1react.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.restapiv1react.entity.EmployeeEntity;
import uz.pdp.restapiv1react.entity.dto.EmployeeRegister;
import uz.pdp.restapiv1react.service.EmployeeService;

@RestController
@RequestMapping(value = "/api/v1/employee")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE ,makeFinal = true)
public class EmployeeController {
    EmployeeService employeeService;

    @GetMapping("/get")
    public ResponseEntity<?> getByPageable(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "6") int size,
            @RequestParam(required = false, defaultValue = "id") String prop
    ) {
        return ResponseEntity.ok(employeeService.getByPagination(page, size, prop));
    }

    @PostMapping("/create")
    public ResponseEntity<?> createEmployee(
            @RequestBody EmployeeRegister employeeRegister
    ) {
        Integer id = employeeService.create(employeeRegister);
        return ResponseEntity.ok(id);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateEmployee(
            @RequestBody EmployeeRegister employeeRegister
    ) {
        Integer id = employeeService.update(employeeRegister);
        return ResponseEntity.ok(id);
    }

    @GetMapping("/getById")
    public ResponseEntity<?> getEmployeeById(
            @RequestParam Integer id
    ) {
        EmployeeEntity employeeEntity = employeeService.getById(id);
        return ResponseEntity.ok(employeeEntity);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteById(
            @RequestParam Integer id
    ) {
        boolean status = employeeService.deleteById(id);
        return ResponseEntity.ok(status);
    }
}

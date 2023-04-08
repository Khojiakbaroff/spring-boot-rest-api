package uz.pdp.restapiv1react.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.restapiv1react.entity.EmployeeEntity;
import uz.pdp.restapiv1react.dto.EmployeeRegister;
import uz.pdp.restapiv1react.service.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employee")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping("/all")
    public ResponseEntity<?> getByPageable(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "6") int size,
            @RequestParam(required = false, defaultValue = "id") String prop
    ) {
        List<EmployeeEntity> employeeEntities = this.employeeService.getByPagination(page, size, prop);
        return ResponseEntity.ok(employeeEntities);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createEmployee(
            @RequestBody EmployeeRegister employeeRegister
    ) {
        Integer id = this.employeeService.create(employeeRegister);
        return ResponseEntity.ok(id);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateEmployee(
            @RequestBody EmployeeRegister employeeRegister
    ) {
        Integer id = this.employeeService.update(employeeRegister);
        return ResponseEntity.ok(id);
    }

    @GetMapping("/getById")
    public ResponseEntity<?> getEmployeeById(
            @RequestParam Integer id
    ) {
        EmployeeEntity employeeEntity = this.employeeService.getById(id);
        return ResponseEntity.ok(employeeEntity);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteById(
            @RequestParam Integer id
    ) {
        boolean status = this.employeeService.deleteById(id);
        return ResponseEntity.ok(status);
    }
}

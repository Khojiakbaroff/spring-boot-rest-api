package uz.pdp.restapiv1react.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import uz.pdp.restapiv1react.customExcaptions.RecordAlreadyExistException;
import uz.pdp.restapiv1react.customExcaptions.RecordNotFoundException;
import uz.pdp.restapiv1react.entity.EmployeeEntity;
import uz.pdp.restapiv1react.entity.dto.EmployeeBuilder;
import uz.pdp.restapiv1react.entity.dto.EmployeeRegister;
import uz.pdp.restapiv1react.repository.EmployeeRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public List<EmployeeEntity> getByPagination(int page, int size, String property) {
        Pageable pageable = getPageable(page, size, property);
        return this.employeeRepository.findAll(pageable).toList();
    }

    public Integer create(EmployeeRegister employee) {
        final String email = employee.getEmail();
        checkByUsername(email);
        EmployeeEntity employeeEntity = EmployeeBuilder.of(employee);
        EmployeeEntity savedEmployeeEntity = this.employeeRepository.save(employeeEntity);
        return savedEmployeeEntity.getId();
    }

    public EmployeeEntity getById(final Integer id) {
        return checkByIdAndGet(id);
    }

    public boolean deleteById(@NonNull final Integer id) {
        checkByIdAndGet(id);
         this.employeeRepository.deleteById(id);
        return !employeeRepository.existsById(id);
    }


    public Integer update(EmployeeRegister employeeRegister) {
        checkByIdAndGet(employeeRegister.getId());
        EmployeeEntity employeeEntity = EmployeeBuilder.of(employeeRegister);
        EmployeeEntity savedEntity =  this.employeeRepository.save(employeeEntity);
        return savedEntity.getId();
    }

    private EmployeeEntity checkByIdAndGet(@NonNull final Integer id) {
        return  this.employeeRepository
                .findById(id)
                .orElseThrow(
                        () -> new RecordNotFoundException(id + " not found"));
    }

    private void checkByUsername(final String username) {
        Optional<EmployeeEntity> existUsername =  this.employeeRepository
                .findByEmail(username);
        if (existUsername.isPresent())
            throw new RecordAlreadyExistException(username + " already exists");
    }

    private Pageable getPageable(int page, int size, String property) {
        return PageRequest.of(page, size, Sort.by(property).ascending());
    }
}

package uz.pdp.restapiv1react.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import uz.pdp.restapiv1react.customExcaptions.RecordAlreadyExistException;
import uz.pdp.restapiv1react.customExcaptions.RecordNotFoundException;
import uz.pdp.restapiv1react.entity.EmployeeEntity;
import uz.pdp.restapiv1react.dto.EmployeeBuilder;
import uz.pdp.restapiv1react.dto.EmployeeRegister;
import uz.pdp.restapiv1react.repository.EmployeeRepository;

import java.util.List;
import java.util.Optional;
import static uz.pdp.restapiv1react.error_messages.Constants.*;
@Slf4j
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

    public boolean deleteById(final Integer id) {
        checkByIdAndGet(id);
        this.employeeRepository.deleteById(id);
        return !employeeRepository.existsById(id);
    }


    public Integer update(EmployeeRegister employeeRegister) {
        checkByIdAndGet(employeeRegister.getId());
        EmployeeEntity employeeEntity = EmployeeBuilder.of(employeeRegister);
        EmployeeEntity savedEntity = this.employeeRepository.save(employeeEntity);
        return savedEntity.getId();
    }

    private EmployeeEntity checkByIdAndGet(@NonNull final Integer id) {
        Optional<EmployeeEntity> employeeEntity = this.employeeRepository.findById(id);

        if (employeeEntity.isEmpty()) {
            log.error(String.format(USER_WITH_ID_NOT_FOUND, id));
            throw new RecordNotFoundException(String.format(USER_WITH_ID_NOT_FOUND, id));
        } else
            return employeeEntity.get();
    }

    private void checkByUsername(final String username) {
        Optional<EmployeeEntity> existUsername = this.employeeRepository
                .findByEmail(username);
        if (existUsername.isPresent()) {
            log.error(String.format(USER_WITH_USERNAME_ALREADY_EXISTS, username));
            throw new RecordAlreadyExistException(String.format(USER_WITH_USERNAME_ALREADY_EXISTS,username));
        }
    }

    private Pageable getPageable(int page, int size, String property) {
        return PageRequest.of(page, size, Sort.by(property).ascending());
    }
}

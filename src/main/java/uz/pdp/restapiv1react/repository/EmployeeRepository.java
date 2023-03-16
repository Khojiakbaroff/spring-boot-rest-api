package uz.pdp.restapiv1react.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import uz.pdp.restapiv1react.entity.EmployeeEntity;

import java.util.Optional;

public interface EmployeeRepository extends CrudRepository<EmployeeEntity, Integer> {
    Optional<EmployeeEntity> findByUsername(String username);
    Page<EmployeeEntity> findAll(Pageable pageable);

}

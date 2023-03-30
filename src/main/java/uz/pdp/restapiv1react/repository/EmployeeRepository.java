package uz.pdp.restapiv1react.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.restapiv1react.entity.EmployeeEntity;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Integer> {
    Optional<EmployeeEntity> findByEmail(String email);

    Page<EmployeeEntity> findAll(Pageable pageable);

}

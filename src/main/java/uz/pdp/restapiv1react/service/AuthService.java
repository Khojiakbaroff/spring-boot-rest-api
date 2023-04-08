package uz.pdp.restapiv1react.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import uz.pdp.restapiv1react.customExcaptions.RecordNotFoundException;
import uz.pdp.restapiv1react.entity.EmployeeEntity;
import uz.pdp.restapiv1react.repository.EmployeeRepository;
import uz.pdp.restapiv1react.security.SecurityEmployee;

@Service
@RequiredArgsConstructor
public class AuthService implements UserDetailsService {

    private final EmployeeRepository employeeRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        EmployeeEntity employeeEntity = employeeRepository
                .findByEmail(email)
                .orElseThrow(() -> new RecordNotFoundException(String.format("That user %s not found", email)));

        return new SecurityEmployee(employeeEntity);

    }
}

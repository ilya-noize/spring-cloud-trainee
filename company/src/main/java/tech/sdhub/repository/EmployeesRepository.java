package tech.sdhub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.sdhub.model.Employee;

import java.util.List;

public interface EmployeesRepository extends JpaRepository<Employee, Long> {

    List<Employee> findByCompanyId(Long id);
}

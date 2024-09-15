package tech.sdhub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.sdhub.model.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {
    boolean existsByNameIgnoreCase(String name);
}


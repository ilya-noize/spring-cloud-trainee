package tech.sdhub.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tech.sdhub.model.Company;
import tech.sdhub.repository.CompanyRepository;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class CompanyController {
    private final CompanyRepository repository;

    @GetMapping("/companies")
    public List<Company> getCompanies(@RequestParam(name = "ids") List<Long> companyIds) {
        log.info("Get Company By IDs {}", companyIds.toString());

        return repository.findAllById(companyIds);
    }
}

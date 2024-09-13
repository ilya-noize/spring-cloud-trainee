package tech.sdhub.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tech.sdhub.model.Company;
import tech.sdhub.service.CompanyService;

import java.util.List;

@RestController
@RequestMapping("/company")
@RequiredArgsConstructor
@Slf4j
public class CompanyController {
    private final CompanyService companyService;

    @PostMapping
    public Company create(@RequestBody Company company) {
        log.info("Create company {}", company);

        return companyService.create(company);
    }

    @PutMapping("/{companyId}")
    public Company update(@PathVariable Long companyId,
                          @RequestParam List<Long> id) {

        return companyService.update(companyId, id);
    }

    @GetMapping("{companyId}")
    public Company get(@PathVariable Long companyId) {
        log.info("get Company staff {}", companyId);

        return companyService.get(companyId);
    }

    @GetMapping
    public List<Company> getAll() {
        log.info("Get Companies staff");

        return companyService.getAll();
    }
}

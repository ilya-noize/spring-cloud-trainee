package tech.sdhub.service;

import tech.sdhub.model.Company;

import java.util.List;

public interface CompanyService {

    Company create(Company company);

    Company update(Long companyId, List<Long> ids);

    Company get(Long companyId);

    List<Company> getAll();
}

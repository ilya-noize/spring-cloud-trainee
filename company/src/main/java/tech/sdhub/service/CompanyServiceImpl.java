package tech.sdhub.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tech.sdhub.dto.EmployeeDto;
import tech.sdhub.exception.BadRequestException;
import tech.sdhub.exception.NotFoundException;
import tech.sdhub.model.Company;
import tech.sdhub.model.Employee;
import tech.sdhub.repository.CompanyRepository;
import tech.sdhub.repository.EmployeesRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository companyRepository;
    private final EmployeesRepository employeesRepository;
    private final RestTemplate restTemplate;

    @Override
    public Company create(Company company) {
        isUniqueNameCompany(company);

        return companyRepository.save(company);
    }

    @Override
    public Company update(Long companyId, List<Long> ids) {
        Company company = getCompanyOrElseThrow(companyId);
        company.setEmployees(List.of(getEmployeesCompanyByIds(ids)));

        return company;
    }

    @Override
    public Company get(Long companyId) {
        Company company = getCompanyOrElseThrow(companyId);
        List<Long> ids = getCompanyEmployeesIds(companyId);
        company.setEmployees(List.of(getEmployeesCompanyByIds(ids)));

        return company;
    }

    private List<Long> getCompanyEmployeesIds(Long companyId) {
        List<Employee> employees = employeesRepository.findByCompanyId(companyId);
        if (employees == null) {
            return Collections.emptyList();
        }

        return employees.stream().map(Employee::getUserId).toList();
    }

    @Override
    public List<Company> getAll() {
        List<Company> employeesInCompany = new ArrayList<>();
        for (Company company : companyRepository.findAll()) {
            List<Long> employeeIds = employeesRepository.findByCompanyId(company.getId())
                    .stream().map(Employee::getId).toList();
            company.setEmployees(List.of(getEmployeesCompanyByIds(employeeIds)));
            employeesInCompany.add(company);
        }

        return employeesInCompany;
    }

    private void isUniqueNameCompany(Company company) {
        if (companyRepository.existsByNameIgnoreCase(company.getName())) {
            throw new BadRequestException("Company name must be unique");
        }
    }

    private Company getCompanyOrElseThrow(Long companyId) {

        return companyRepository.findById(companyId).orElseThrow(
                () -> new NotFoundException(format("Company ID:%s not found", companyId))
        );
    }

    private EmployeeDto[] getEmployeesCompanyByIds(List<Long> employeeIds) {
        if (employeeIds.isEmpty()) {
            return new EmployeeDto[0];
        }

        return restTemplate.getForObject(getUrl(employeeIds), EmployeeDto[].class);
    }

    private String getUrl(List<Long> ids) {
        StringBuilder sb = new StringBuilder("http://localhost:8082/users/?");
        String params = "&ids=X".repeat(ids.size()).substring(1);
        for (Long id: ids) {
            params = params.replaceFirst("X", Long.toString(id));
        }

        return sb.append(params).toString();
    }
}

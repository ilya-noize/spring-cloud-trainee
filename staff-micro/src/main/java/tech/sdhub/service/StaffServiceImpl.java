package tech.sdhub.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tech.sdhub.dto.CompanyDto;
import tech.sdhub.dto.UserDto;
import tech.sdhub.dto.CompanyStaffDto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StaffServiceImpl implements StaffService {
    private final RestTemplate restTemplate;

    @Override
    public List<CompanyStaffDto> getCompaniesStaff(List<Long> companyIds) {
        String params = getParams(companyIds);
        String urlCompany = String.format(SERVICE_COMPANY_URL + "/companies?%s", params);
        CompanyDto[] companies = restTemplate.getForObject(urlCompany, CompanyDto[].class);
        assert companies != null;
        UserDto[] employeesFromAllCompanies = getEmployeesFromCompanies(companies);
        assert employeesFromAllCompanies != null;
        return getCompaniesStaff(companies, employeesFromAllCompanies);
    }

    private UserDto[] getEmployeesFromCompanies(CompanyDto[] companies) {
        List<Long> employeeIds = getEmployeeIds(companies);
        String params = getParams(employeeIds);
        String urlEmployees = String.format(SERVICE_USER_URL + "/users?%s", params);
        return restTemplate.getForObject(urlEmployees, UserDto[].class);
    }

    /**
     * @param ids values of parameter
     * @return string 'ids=X&ids=X&ids=X&ids=X&ids=X&ids=X&ids=X' for URL
     */
    private String getParams(List<Long> ids) {
        String params = "&ids=X".repeat(ids.size()).substring(1);
        for(long id: ids) {
            params = params.replace("X", Long.toString(id));
        }
        return params;
    }

    private List<Long> getEmployeeIds(CompanyDto[] companies) {
        if (companies.length == 1) {
            return companies[0].getEmployees();
        }
        List<Long> employeeIds = new ArrayList<>();
        for(CompanyDto company : companies) {
            employeeIds.addAll(company.getEmployees());
        }
        return employeeIds.stream().distinct().toList();
    }

    private List<CompanyStaffDto> getCompaniesStaff(CompanyDto[] companies, UserDto[] UserDtos) {
        List<CompanyStaffDto> companyStaffDtos = new ArrayList<>();
        for(CompanyDto company : companies) {
            List<UserDto> employeeDtos = Arrays.stream(UserDtos)
                    .filter(userDto -> company.getEmployees().contains(userDto.getId())).toList();
            companyStaffDtos.add(CompanyStaffDto.builder()
                    .id(company.getId())
                    .name(company.getName())
                    .budget(company.getBudget())
                    .employees(employeeDtos)
                    .build());
        }
        return companyStaffDtos;
    }
}

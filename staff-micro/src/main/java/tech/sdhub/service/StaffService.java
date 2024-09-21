package tech.sdhub.service;

import tech.sdhub.dto.CompanyStaffDto;

import java.util.List;

public interface StaffService {
    String SERVICE_COMPANY_URL = "http://COMPANY/api";
    String SERVICE_USER_URL = "http://USERS/api";

    List<CompanyStaffDto> getCompaniesStaff(List<Long> companyIds);

}

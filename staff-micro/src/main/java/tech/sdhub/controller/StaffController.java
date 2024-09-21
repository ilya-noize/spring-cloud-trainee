package tech.sdhub.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tech.sdhub.dto.CompanyStaffDto;
import tech.sdhub.service.StaffService;

import java.util.List;

/**
 * import org.springframework.cloud.client.discovery.DiscoveryClient; <br/>
 * Debug with DiscoveryClient
 */
@RestController
@RequiredArgsConstructor
@Slf4j
public class StaffController {
    private final StaffService service;

    @PostMapping("/company/staff")
    public List<CompanyStaffDto> getCompaniesStaff(@RequestParam(name = "ids") List<Long> companyIds) {
        log.info("get companies with id={}",companyIds);

        return service.getCompaniesStaff(companyIds);
    }

}

package tech.sdhub.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompanyStaffDto {
    private Long id;
    private String name;
    private String budget;
    private List<UserDto> employees;
}
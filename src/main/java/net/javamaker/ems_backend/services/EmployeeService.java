package net.javamaker.ems_backend.services;

import net.javamaker.ems_backend.dto.EmployeeRequestDto;
import net.javamaker.ems_backend.dto.EmployeeResponseDto;

import java.util.List;

public interface EmployeeService {

    List<EmployeeResponseDto> getAllEmployees();

    EmployeeResponseDto getEmployeeById(Long id);

    EmployeeResponseDto createEmployee(EmployeeRequestDto request);

    EmployeeResponseDto updateEmployee(Long id, EmployeeRequestDto request);

    void deleteEmployee(Long id);
}

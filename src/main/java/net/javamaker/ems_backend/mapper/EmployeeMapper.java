package net.javamaker.ems_backend.mapper;

import net.javamaker.ems_backend.dto.EmployeeRequestDto;
import net.javamaker.ems_backend.dto.EmployeeResponseDto;
import net.javamaker.ems_backend.entity.Employee;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {

    public Employee toEntity(EmployeeRequestDto request) {
        Employee employee = new Employee();
        updateEntity(employee, request);
        return employee;
    }

    public void updateEntity(Employee employee, EmployeeRequestDto request) {
        employee.setFirstName(request.getFirstName());
        employee.setLastName(request.getLastName());
        employee.setEmail(request.getEmail());
        employee.setDepartment(request.getDepartment());
        employee.setJobTitle(request.getJobTitle());
        employee.setSalary(request.getSalary());
    }

    public EmployeeResponseDto toResponse(Employee employee) {
        return new EmployeeResponseDto(
                employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail(),
                employee.getDepartment(),
                employee.getJobTitle(),
                employee.getSalary()
        );
    }
}

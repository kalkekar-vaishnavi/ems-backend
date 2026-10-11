package net.javamaker.ems_backend.services.impl;

import net.javamaker.ems_backend.dto.EmployeeRequestDto;
import net.javamaker.ems_backend.dto.EmployeeResponseDto;
import net.javamaker.ems_backend.entity.Employee;
import net.javamaker.ems_backend.exception.ResourceNotFoundException;
import net.javamaker.ems_backend.mapper.EmployeeMapper;
import net.javamaker.ems_backend.repository.EmployeeRepository;
import net.javamaker.ems_backend.services.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, EmployeeMapper employeeMapper) {
        this.employeeRepository = employeeRepository;
        this.employeeMapper = employeeMapper;
    }

    @Override
    public List<EmployeeResponseDto> getAllEmployees() {
        return employeeRepository.findAll().stream()
                .map(employeeMapper::toResponse)
                .toList();
    }

    @Override
    public EmployeeResponseDto getEmployeeById(Long id) {
        return employeeMapper.toResponse(findEmployee(id));
    }

    @Override
    public EmployeeResponseDto createEmployee(EmployeeRequestDto request) {
        Employee employee = employeeMapper.toEntity(request);
        return employeeMapper.toResponse(employeeRepository.save(employee));
    }

    @Override
    public EmployeeResponseDto updateEmployee(Long id, EmployeeRequestDto request) {
        Employee employee = findEmployee(id);
        employeeMapper.updateEntity(employee, request);
        return employeeMapper.toResponse(employeeRepository.save(employee));
    }

    @Override
    public void deleteEmployee(Long id) {
        employeeRepository.delete(findEmployee(id));
    }

    private Employee findEmployee(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));
    }
}

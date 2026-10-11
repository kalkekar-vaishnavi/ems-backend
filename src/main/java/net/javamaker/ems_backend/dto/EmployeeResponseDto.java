package net.javamaker.ems_backend.dto;

import java.math.BigDecimal;

public class EmployeeResponseDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String department;
    private String jobTitle;
    private BigDecimal salary;

    public EmployeeResponseDto(Long id, String firstName, String lastName, String email,
                               String department, String jobTitle, BigDecimal salary) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.department = department;
        this.jobTitle = jobTitle;
        this.salary = salary;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getDepartment() {
        return department;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public BigDecimal getSalary() {
        return salary;
    }
}

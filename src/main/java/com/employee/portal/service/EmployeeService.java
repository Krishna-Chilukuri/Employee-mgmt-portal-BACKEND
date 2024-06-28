package com.employee.portal.service;

import com.employee.portal.model.Employee;

import java.util.List;

public interface EmployeeService {
    Employee saveEmployee(Employee emp);
    List<Employee> getAllEmployees();
    Employee getEmployeeById(long empId);
    Employee updateEmployee(Employee emp, long empId);
    void deleteEmployee(long empId);
    long getRankCount(long empRank);
    List<Long> getEmployeesByRank(long empRank);
    List<Long> getEmployeesByReportsTo(long empId, long newSuperiorRank);
    void updateReportsTo(long reportsTo, long empId);
    List<Long> getBossIds();
}

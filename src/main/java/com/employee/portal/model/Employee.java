package com.employee.portal.model;

import jakarta.persistence.*;

@Entity
@Table(name="employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "employee_id")
    private long employeeId;

    @Column(name="employee_name")
    private String employeeName;

    @Column(name="employee_rank")
    private long employeeRank;

    @Column(name = "reports_to")
    private long reportsTo;

    public long getEmployeeId() {
        return employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public long getEmployeeRank() {
        return employeeRank;
    }

    public void setEmployeeRank(long employeeRank) {
        this.employeeRank = employeeRank;
    }

    public long getReportsTo() {
        return reportsTo;
    }

    public void setReportsTo(long reportsTo) {
        this.reportsTo = reportsTo;
    }

    public void setEmployeeId(long employeeId) {
        this.employeeId = employeeId;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId=" + employeeId +
                ", employeeName='" + employeeName + '\'' +
                ", employeeRank=" + employeeRank +
                ", reportsTo=" + reportsTo +
                '}';
    }
}

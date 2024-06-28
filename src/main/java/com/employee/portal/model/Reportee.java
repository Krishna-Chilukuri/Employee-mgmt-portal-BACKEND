package com.employee.portal.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "reportees")
public class Reportee {
    @Id
    @Column(name = "reportee")
    private long reportee;

    @Column(name = "employee_id")
    private long employee_id;

    public long getReportee() {
        return reportee;
    }

    public void setReportee(long reportee) {
        this.reportee = reportee;
    }

    public long getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(long employee_id) {
        this.employee_id = employee_id;
    }

    @Override
    public String toString() {
        return "Reportee{" +
                "reportee=" + reportee +
                ", employee_id=" + employee_id +
                '}';
    }
}

package com.employee.portal.service;

import com.employee.portal.model.Reportee;

import java.util.List;

public interface ReporteeService {
    Reportee saveReportee(Reportee reportee);
    void removeReportee(long reporteeId);
    List<Long> getReporteesById(long empId);
}

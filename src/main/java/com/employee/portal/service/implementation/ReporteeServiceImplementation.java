package com.employee.portal.service.implementation;

import com.employee.portal.exception.ResourceNotFoundException;
import com.employee.portal.model.Reportee;
import com.employee.portal.repository.ReporteesRepository;
import com.employee.portal.service.ReporteeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReporteeServiceImplementation implements ReporteeService {
    public ReporteesRepository reporteesRepository;

    public ReporteeServiceImplementation(ReporteesRepository reporteesRepository) {
        super();
        this.reporteesRepository = reporteesRepository;
    }
    @Override
    public Reportee saveReportee(Reportee reportee) {
        return reporteesRepository.save(reportee);
    }

    @Override
    public void removeReportee(long reporteeId) {
        reporteesRepository.findById(reporteeId).orElseThrow(() -> new ResourceNotFoundException("Reportee", "Reportee Id", reporteeId));
        reporteesRepository.deleteById(reporteeId);
    }

    @Override
    public List<Long> getReporteesById(long empId) {
        return reporteesRepository.findReporteesByEmpId(empId);
    }
}

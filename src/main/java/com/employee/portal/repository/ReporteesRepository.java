package com.employee.portal.repository;

import com.employee.portal.model.Reportee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReporteesRepository extends JpaRepository<Reportee, Long> {

    @Query("SELECT reportee from Reportee WHERE employee_id = :empId")
    List<Long> findReporteesByEmpId(long empId);
}

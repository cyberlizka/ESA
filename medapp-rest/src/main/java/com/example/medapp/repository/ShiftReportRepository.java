package com.example.medapp.repository;

import com.example.medapp.model.ShiftReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShiftReportRepository extends JpaRepository<ShiftReport, Integer> {

    List<ShiftReport> findByDoctorId(Integer doctorId);

    List<ShiftReport> findByDoctorIdOrderByReportDateDesc(Integer doctorId);
}
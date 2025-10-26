package com.example.medapp.service;

import com.example.medapp.model.ShiftReport;
import com.example.medapp.repository.ShiftReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class ShiftReportService {

    private final ShiftReportRepository shiftReportRepository;

    @Autowired
    public ShiftReportService(ShiftReportRepository shiftReportRepository) {
        this.shiftReportRepository = shiftReportRepository;
    }

    @Transactional(readOnly = true)
    public List<ShiftReport> getAllShiftReports() {
        return shiftReportRepository.findAll();
    }

    @Transactional(readOnly = true)
    public ShiftReport getShiftReportById(Integer id) {
        return shiftReportRepository.findById(id).orElse(null);
    }

    @Transactional
    public void addShiftReport(ShiftReport shiftReport) {
        shiftReportRepository.save(shiftReport);
    }

    @Transactional
    public void updateShiftReport(ShiftReport shiftReport) {
        shiftReportRepository.save(shiftReport);
    }

    @Transactional
    public void deleteShiftReport(Integer id) {
        shiftReportRepository.deleteById(id);
    }



    @Transactional(readOnly = true)
    public ShiftReport getLatestShiftReportForDoctor(Integer doctorId) {
        List<ShiftReport> reports = shiftReportRepository.findByDoctorIdOrderByReportDateDesc(doctorId);
        return reports.isEmpty() ? null : reports.get(0); 
    }
}

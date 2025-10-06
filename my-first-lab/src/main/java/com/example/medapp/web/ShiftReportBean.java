package com.example.medapp.web;

import com.example.medapp.model.Doctor;
import com.example.medapp.model.ShiftReport;
import com.example.medapp.service.DoctorService;
import com.example.medapp.service.ShiftReportService;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Named
@SessionScoped
public class ShiftReportBean implements Serializable {

    @Inject
    private ShiftReportService shiftReportService;

    @Inject
    private DoctorService doctorService;

    private ShiftReport shiftReport = new ShiftReport();
    private List<ShiftReport> shiftReports;
    private List<ShiftReport> shiftReportsByDoctor;
    private Integer selectedDoctorId;
    private String message;

    @PostConstruct
    public void init() {
        loadShiftReports();
    }

    public void loadShiftReports() {
        shiftReports = shiftReportService.getAllShiftReports();
    }

    public String addShiftReport() {
        
        Doctor doctor = doctorService.getDoctorById(selectedDoctorId);
        shiftReport.setDoctor(doctor);
        shiftReport.setReportDate(new Date());

        shiftReportService.addShiftReport(shiftReport);
        loadShiftReports();
        shiftReport = new ShiftReport(); 
        message = "Отчет успешно добавлен!";
        return "list?faces-redirect=true";
    }

    public String deleteShiftReport(Integer id) {
        shiftReportService.deleteShiftReport(id);
        loadShiftReports();
        message = "Отчет успешно удален!";
        return "list?faces-redirect=true";
    }

    public String editShiftReport(Integer id) {
        this.shiftReport = shiftReportService.getShiftReportById(id);
        this.selectedDoctorId = shiftReport.getDoctor().getId(); 
        return "edit?faces-redirect=true";
    }

    public String updateShiftReport() {
         
        Doctor doctor = doctorService.getDoctorById(selectedDoctorId);
        shiftReport.setDoctor(doctor);
        shiftReportService.updateShiftReport(shiftReport);
        loadShiftReports();
        shiftReport = new ShiftReport(); 
        message = "Отчет успешно обновлен!";
        return "list?faces-redirect=true";
    }

     public String viewShiftReportsByDoctor(Integer doctorId) {
        this.shiftReportsByDoctor = shiftReportService.getShiftReportsByDoctor(doctorId);
        return "reportsByDoctor?faces-redirect=true";
    }

    public ShiftReport getShiftReport() {
        return shiftReport;
    }

    public void setShiftReport(ShiftReport shiftReport) {
        this.shiftReport = shiftReport;
    }

    public List<ShiftReport> getShiftReports() {
        return shiftReports;
    }

    public List<ShiftReport> getShiftReportsByDoctor() {
        return shiftReportsByDoctor;
    }

    public Integer getSelectedDoctorId() {
        return selectedDoctorId;
    }

    public void setSelectedDoctorId(Integer selectedDoctorId) {
        this.selectedDoctorId = selectedDoctorId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Doctor> getAllDoctors() {
        return doctorService.getAllDoctors();
    }
}

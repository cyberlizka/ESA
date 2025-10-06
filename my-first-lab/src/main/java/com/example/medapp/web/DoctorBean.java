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
import java.util.List;

@Named
@SessionScoped
public class DoctorBean implements Serializable {

    @Inject
    private DoctorService doctorService;

    @Inject
    private ShiftReportService shiftReportService;

    private Doctor doctor = new Doctor();
    private List<Doctor> doctors;
    private String message;

    @PostConstruct
    public void init() {
        loadDoctors();
    }

    public void loadDoctors() {        
        doctors = doctorService.getAllDoctors(); 
    }

    public String addDoctor() {
        doctorService.addDoctor(doctor);
        loadDoctors();
        doctor = new Doctor();
        message = "Доктор успешно добавлен!";
        return "list?faces-redirect=true";
    }

    public String deleteDoctor(Integer id) {
        doctorService.deleteDoctor(id);
        loadDoctors();
        message = "Доктор успешно удален!";
        return "list?faces-redirect=true";
    }

    public String editDoctor(Integer id) {
        this.doctor = doctorService.getDoctorById(id);
        return "edit?faces-redirect=true";
    }

    public String updateDoctor() {
        doctorService.updateDoctor(doctor);
        loadDoctors();
        doctor = new Doctor();
        message = "Доктор успешно обновлен!";
        return "list?faces-redirect=true";
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public List<Doctor> getDoctors() {
        return doctors;
    }

    public String getMessage() {
        return message;
    }


    public ShiftReport getLatestShiftReportForDoctor(Doctor doctor) {
        if (doctor == null || doctor.getId() == null) {
            return null;
        }
        return shiftReportService.getLatestShiftReportForDoctor(doctor.getId());
    }


    public String getShortReportContent(Object contentOrReport, Number maxLengthNumber) {
        if (contentOrReport == null) {
            return "";
        }

        String fullContent;

        if (contentOrReport instanceof ShiftReport) {
            ShiftReport report = (ShiftReport) contentOrReport;
            fullContent = report.getNotes();
        } else {
         
            fullContent = contentOrReport.toString();
        }

        if (fullContent == null || fullContent.isEmpty()) {
            return "";
        }

        int maxLength = 100; 
        if (maxLengthNumber != null) {
            try {
                maxLength = Math.max(0, maxLengthNumber.intValue());
            } catch (Exception ex) {
               
            }
        }

        if (fullContent.length() <= maxLength) {
            return fullContent;
        }
        return fullContent.substring(0, maxLength) + "...";
    }

    public String getShortReportContent(String fullContent, Long maxLength) {
        return getShortReportContent((Object) fullContent, maxLength);
    }
}

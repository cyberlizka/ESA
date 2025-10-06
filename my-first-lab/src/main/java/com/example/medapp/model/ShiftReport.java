package com.example.medapp.model; 

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "shift_report") 
public class ShiftReport implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Integer id;

    @ManyToOne 
    @JoinColumn(name = "doctor_id", nullable = false) 
    private Doctor doctor;

    @Temporal(TemporalType.DATE) 
    @Column(name = "report_date") 
    private Date reportDate;

    @Column(name = "notes", columnDefinition = "TEXT") 
    private String notes;


    public ShiftReport() {
    }

    public ShiftReport(Doctor doctor, Date reportDate, String notes) {
        this.doctor = doctor;
        this.reportDate = reportDate;
        this.notes = notes;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Date getReportDate() {
        return reportDate;
    }

    public void setReportDate(Date reportDate) {
        this.reportDate = reportDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "ShiftReport{" +
               "id=" + id +
               ", doctor=" + (doctor != null ? doctor.getName() : "null") + 
               ", reportDate=" + reportDate +
               ", notes='" + notes + '\'' +
               '}';
    }
}

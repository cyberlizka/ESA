package com.example.medapp.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "doctor")
public class Doctor implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String specialty;

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ShiftReport> shiftReports = new ArrayList<>();

  
    public Doctor() {}

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getSpecialty() { return specialty; }
    public void setSpecialty(String specialty) { this.specialty = specialty; }

    public List<ShiftReport> getShiftReports() { return shiftReports; }
    public void setShiftReports(List<ShiftReport> shiftReports) { this.shiftReports = shiftReports; }

    public void addShiftReport(ShiftReport r) {
        shiftReports.add(r);
        r.setDoctor(this);
    }

    public void removeShiftReport(ShiftReport r) {
        shiftReports.remove(r);
        r.setDoctor(null);
    }
}

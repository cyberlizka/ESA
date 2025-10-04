package com.example.medapp.web;

import com.example.medapp.model.Doctor;
import com.example.medapp.service.DoctorService;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named("doctorBean")
@SessionScoped
public class DoctorBean implements Serializable {

    @EJB
    private DoctorService service;

    private Doctor doctor = new Doctor();
    private Integer editId;

    public List<Doctor> getDoctors() {
        return service.findAll();
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public String create() {
        service.create(doctor);
        doctor = new Doctor();
        return "list.xhtml?faces-redirect=true";
    }

    public String delete(Integer id) {
        service.delete(id);
        return "list.xhtml?faces-redirect=true";
    }

    public String edit(Integer id) {
        Doctor d = service.find(id);
        if (d != null) {
            doctor = d;
            editId = id;
            return "edit.xhtml?faces-redirect=true";
        }
        return "list.xhtml?faces-redirect=true";
    }

    public String save() {
        if (editId != null) {
            service.update(doctor);
            editId = null;
        } else {
            service.create(doctor);
        }
        doctor = new Doctor();
        return "list.xhtml?faces-redirect=true";
    }
}

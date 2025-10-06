package com.example.medapp.service;

import com.example.medapp.model.Doctor;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class DoctorService {

    @PersistenceContext(unitName = "medappPU")
    private EntityManager em;

    public List<Doctor> getAllDoctors() {
        return em.createQuery("SELECT d FROM Doctor d", Doctor.class).getResultList();
    }

    public Doctor getDoctorById(Integer id) {
        return em.find(Doctor.class, id);
    }

    public void addDoctor(Doctor doctor) {
        em.persist(doctor);
    }

    public void updateDoctor(Doctor doctor) {
        em.merge(doctor);
    }

    public void deleteDoctor(Integer id) {
        Doctor doctor = em.find(Doctor.class, id);
        if (doctor != null) {
            em.remove(doctor);
        }
    }
}

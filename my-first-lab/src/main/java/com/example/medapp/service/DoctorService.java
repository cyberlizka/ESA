package com.example.medapp.service;

import com.example.medapp.model.Doctor;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class DoctorService {

    @PersistenceContext(unitName = "medPU")
    private EntityManager em;

    public Doctor create(Doctor d) {
        em.persist(d);
        return d;
    }

    public Doctor update(Doctor d) {
        return em.merge(d);
    }

    public void delete(Integer id) {
        Doctor d = em.find(Doctor.class, id);
        if (d != null) em.remove(d);
    }

    public Doctor find(Integer id) {
        return em.find(Doctor.class, id);
    }

    public List<Doctor> findAll() {
        return em.createQuery("SELECT d FROM Doctor d ORDER BY d.id", Doctor.class).getResultList();
    }
}

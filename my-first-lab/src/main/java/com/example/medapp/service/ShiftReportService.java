package com.example.medapp.service;

import com.example.medapp.model.ShiftReport;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import java.util.List;

@Stateless
public class ShiftReportService {

    @PersistenceContext(unitName = "medappPU")
    private EntityManager em;

    public List<ShiftReport> getAllShiftReports() {
        return em.createQuery("SELECT sr FROM ShiftReport sr", ShiftReport.class).getResultList();
    }

    public ShiftReport getShiftReportById(Integer id) {
        return em.find(ShiftReport.class, id);
    }

    public void addShiftReport(ShiftReport shiftReport) {
        em.persist(shiftReport);
    }

    public void updateShiftReport(ShiftReport shiftReport) {
        em.merge(shiftReport);
    }

    public void deleteShiftReport(Integer id) {
        ShiftReport shiftReport = em.find(ShiftReport.class, id);
        if (shiftReport != null) {
            em.remove(shiftReport);
        }
    }

    public List<ShiftReport> getShiftReportsByDoctor(Integer doctorId) {
        TypedQuery<ShiftReport> query = em.createQuery(
            "SELECT sr FROM ShiftReport sr WHERE sr.doctor.id = :doctorId", ShiftReport.class);
        query.setParameter("doctorId", doctorId);
        return query.getResultList();
    }

    public ShiftReport getLatestShiftReportForDoctor(Integer doctorId) {
        TypedQuery<ShiftReport> query = em.createQuery(
            "SELECT sr FROM ShiftReport sr WHERE sr.doctor.id = :doctorId ORDER BY sr.reportDate DESC", ShiftReport.class);
        query.setParameter("doctorId", doctorId);
        query.setMaxResults(1); 
        List<ShiftReport> reports = query.getResultList();
        return reports.isEmpty() ? null : reports.get(0);
    }
}

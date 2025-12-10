package com.example.medapp.service;

import com.example.medapp.model.Doctor;
import com.example.medapp.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class DoctorService {

    private final DoctorRepository doctorRepository;

    @Autowired
    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @Transactional(readOnly = true)
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Doctor getDoctorById(Integer id) {
        return doctorRepository.findById(id).orElse(null);
    }

    @Transactional
    public void addDoctor(Doctor doctor) {
        doctorRepository.save(doctor);
    }

    @Transactional
    public void updateDoctor(Doctor doctor) {
        doctorRepository.save(doctor);
    }

    @Transactional
    public void deleteDoctor(Integer id) {
        doctorRepository.deleteById(id);
    }
}
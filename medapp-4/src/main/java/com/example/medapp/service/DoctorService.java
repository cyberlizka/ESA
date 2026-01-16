package com.example.medapp.service;

import com.example.medapp.dto.EntityChangeMessage;
import com.example.medapp.model.Doctor;
import com.example.medapp.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DoctorService {

    private final DoctorRepository doctorRepository;

    @Autowired
    private JmsTemplate jmsTemplate;

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

        sendChangeMessage("Doctor", doctor.getId().toString(), "INSERT", doctor.toString());
    }

    @Transactional
    public void updateDoctor(Doctor doctor) {
        Doctor savedDoctor = doctorRepository.save(doctor);

        sendChangeMessage("Doctor", savedDoctor.getId().toString(), "UPDATE", savedDoctor.toString());
    }

    @Transactional
    public void deleteDoctor(Integer id) {
        Doctor doctor = doctorRepository.findById(id).orElse(null);
        if (doctor != null) {
            doctorRepository.deleteById(id);

            sendChangeMessage("Doctor", id.toString(), "DELETE", doctor.toString());
        }
    }

    private void sendChangeMessage(String entityName, String entityId, String changeType, String changeDetails) {
        EntityChangeMessage message = new EntityChangeMessage(entityName, entityId, changeType, changeDetails);
        System.out.println("Sending JMS message: " + message);
        jmsTemplate.convertAndSend("entityChangeTopic", message);
    }
}

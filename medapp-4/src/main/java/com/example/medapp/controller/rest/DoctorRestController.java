package com.example.medapp.controller.rest;

import com.example.medapp.model.Doctor;
import com.example.medapp.service.DoctorService;
import com.example.medapp.util.SimpleXmlUtils;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctors")
public class DoctorRestController {

    private final DoctorService doctorService;
    private final SimpleXmlUtils xmlUtils;

    public DoctorRestController(DoctorService doctorService, SimpleXmlUtils xmlUtils) {
        this.doctorService = doctorService;
        this.xmlUtils = xmlUtils;
    }

    @GetMapping(value = "/xml", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<String> getAllDoctorsAsXml() {
        try {
            List<Doctor> doctors = doctorService.getAllDoctors();
            if (doctors == null || doctors.isEmpty()) {
                return ResponseEntity.notFound().build();
            }

            DoctorsList doctorsList = new DoctorsList(doctors);
            String xml = xmlUtils.toXmlWithXsl(doctorsList, "/xsl/doctors.xsl");
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_XML)
                    .body(xml);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping(value = "/{id}/xml", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<String> getDoctorByIdAsXml(@PathVariable Integer id) {
        try {
            Doctor doctor = doctorService.getDoctorById(id);
            if (doctor == null) {
                return ResponseEntity.notFound().build();
            }

            DoctorsList doctorsList = new DoctorsList(List.of(doctor));
            String xml = xmlUtils.toXmlWithXsl(doctorsList, "/xsl/doctors.xsl");
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_XML)
                    .body(xml);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping(value = "/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Doctor>> getAllDoctorsAsJson() {
        return ResponseEntity.ok(doctorService.getAllDoctors());
    }

    @GetMapping(value = "/{id}/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Doctor> getDoctorByIdAsJson(@PathVariable Integer id) {
        Doctor doctor = doctorService.getDoctorById(id);
        return doctor != null
                ? ResponseEntity.ok(doctor)
                : ResponseEntity.notFound().build();
    }

    @PostMapping("/new")
    public ResponseEntity<Doctor> createDoctor(@RequestBody Doctor doctor) {
        doctorService.addDoctor(doctor);
        return ResponseEntity.status(HttpStatus.CREATED).body(doctor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Doctor> updateDoctor(@PathVariable Integer id,
                                               @RequestBody Doctor doctor) {
        doctor.setId(id);
        doctorService.updateDoctor(doctor);
        return ResponseEntity.ok(doctor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDoctor(@PathVariable Integer id) {
        doctorService.deleteDoctor(id);
        return ResponseEntity.noContent().build();
    }

    @JacksonXmlRootElement(localName = "doctors")
    private static class DoctorsList {

        @JacksonXmlElementWrapper(useWrapping = false)
        private List<Doctor> doctor;

        public DoctorsList() {}

        public DoctorsList(List<Doctor> doctor) {
            this.doctor = doctor;
        }

        public List<Doctor> getDoctor() {
            return doctor;
        }

        public void setDoctor(List<Doctor> doctor) {
            this.doctor = doctor;
        }
    }
}

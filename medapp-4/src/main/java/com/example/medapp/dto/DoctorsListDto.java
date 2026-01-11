package com.example.medapp.dto;

import com.example.medapp.model.Doctor;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.List;

@JacksonXmlRootElement(localName = "doctors")
public class DoctorsListDto {

    @JacksonXmlElementWrapper(useWrapping = false)
    private List<Doctor> doctor;

    public DoctorsListDto() {
    }

    public DoctorsListDto(List<Doctor> doctor) {
        this.doctor = doctor;
    }

    public List<Doctor> getDoctor() {
        return doctor;
    }

    public void setDoctor(List<Doctor> doctor) {
        this.doctor = doctor;
    }
}

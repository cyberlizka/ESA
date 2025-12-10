package com.example.medapp.dto;

import com.example.medapp.model.ShiftReport;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.List;

@JacksonXmlRootElement(localName = "shiftReports")
public class ShiftReportsListDto {

    @JacksonXmlElementWrapper(useWrapping = false)
    private List<ShiftReport> shiftReport;

    public ShiftReportsListDto() {
    }

    public ShiftReportsListDto(List<ShiftReport> shiftReport) {
        this.shiftReport = shiftReport;
    }

    public List<ShiftReport> getShiftReport() {
        return shiftReport;
    }

    public void setShiftReport(List<ShiftReport> shiftReport) {
        this.shiftReport = shiftReport;
    }
}

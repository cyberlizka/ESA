package com.example.medapp.controller.rest;

import com.example.medapp.model.ShiftReport;
import com.example.medapp.service.ShiftReportService;
import com.example.medapp.util.SimpleXmlUtils;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shiftReports")
public class ShiftReportRestController {

    private final ShiftReportService shiftReportService;
    private final SimpleXmlUtils xmlUtils;

    public ShiftReportRestController(ShiftReportService shiftReportService,
                                     SimpleXmlUtils xmlUtils) {
        this.shiftReportService = shiftReportService;
        this.xmlUtils = xmlUtils;
    }

    @GetMapping(value = "/xml", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<String> getAllShiftReportsAsXml() {
        try {
            List<ShiftReport> reports = shiftReportService.getAllShiftReports();
            if (reports == null || reports.isEmpty()) {
                return ResponseEntity.notFound().build();
            }

            ShiftReportsList reportsList = new ShiftReportsList(reports);
            String xml = xmlUtils.toXmlWithXsl(reportsList, "/xsl/shiftReports.xsl");
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_XML)
                    .body(xml);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping(value = "/{id}/xml", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<String> getShiftReportByIdAsXml(@PathVariable Integer id) {
        try {
            ShiftReport report = shiftReportService.getShiftReportById(id);
            if (report == null) {
                return ResponseEntity.notFound().build();
            }

            ShiftReportsList reportsList = new ShiftReportsList(List.of(report));
            String xml = xmlUtils.toXmlWithXsl(reportsList, "/xsl/shiftReports.xsl");
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_XML)
                    .body(xml);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping(value = "/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ShiftReport>> getAllShiftReportsAsJson() {
        return ResponseEntity.ok(shiftReportService.getAllShiftReports());
    }

    @GetMapping(value = "/{id}/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ShiftReport> getShiftReportByIdAsJson(@PathVariable Integer id) {
        ShiftReport report = shiftReportService.getShiftReportById(id);
        return report != null
                ? ResponseEntity.ok(report)
                : ResponseEntity.notFound().build();
    }

    @PostMapping("/new")
    public ResponseEntity<ShiftReport> createShiftReport(@RequestBody ShiftReport shiftReport) {
        shiftReportService.addShiftReport(shiftReport);
        return ResponseEntity.status(HttpStatus.CREATED).body(shiftReport);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ShiftReport> updateShiftReport(@PathVariable Integer id,
                                                         @RequestBody ShiftReport shiftReport) {
        shiftReport.setId(id);
        shiftReportService.updateShiftReport(shiftReport);
        return ResponseEntity.ok(shiftReport);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteShiftReport(@PathVariable Integer id) {
        shiftReportService.deleteShiftReport(id);
        return ResponseEntity.noContent().build();
    }

    @JacksonXmlRootElement(localName = "shiftReports")
    private static class ShiftReportsList {

        @JacksonXmlElementWrapper(useWrapping = false)
        private List<ShiftReport> shiftReport;

        public ShiftReportsList() {}

        public ShiftReportsList(List<ShiftReport> shiftReport) {
            this.shiftReport = shiftReport;
        }

        public List<ShiftReport> getShiftReport() {
            return shiftReport;
        }

        public void setShiftReport(List<ShiftReport> shiftReport) {
            this.shiftReport = shiftReport;
        }
    }
}

package com.example.medapp.service;

import com.example.medapp.dto.EntityChangeMessage;
import com.example.medapp.model.ShiftReport;
import com.example.medapp.repository.ShiftReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ShiftReportService {

    private final ShiftReportRepository shiftReportRepository;

    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    public ShiftReportService(ShiftReportRepository shiftReportRepository) {
        this.shiftReportRepository = shiftReportRepository;
    }

    @Transactional(readOnly = true)
    public List<ShiftReport> getAllShiftReports() {
        return shiftReportRepository.findAll();
    }

    @Transactional(readOnly = true)
    public ShiftReport getShiftReportById(Integer id) {
        return shiftReportRepository.findById(id).orElse(null);
    }

    @Transactional
    public void addShiftReport(ShiftReport shiftReport) {
        ShiftReport savedReport = shiftReportRepository.save(shiftReport);

        sendChangeMessage("ShiftReport", savedReport.getId().toString(), "INSERT", savedReport.toString());
    }

    @Transactional
    public void updateShiftReport(ShiftReport shiftReport) {
        ShiftReport savedReport = shiftReportRepository.save(shiftReport);

        sendChangeMessage("ShiftReport", savedReport.getId().toString(), "UPDATE", savedReport.toString());
    }

    @Transactional
    public void deleteShiftReport(Integer id) {
        ShiftReport report = shiftReportRepository.findById(id).orElse(null);
        if (report != null) {
            shiftReportRepository.deleteById(id);

            sendChangeMessage("ShiftReport", id.toString(), "DELETE", report.toString());
        }
    }

    private void sendChangeMessage(String entityName, String entityId, String changeType, String changeDetails) {
        EntityChangeMessage message = new EntityChangeMessage(entityName, entityId, changeType, changeDetails);
        System.out.println("Sending JMS message: " + message);
        jmsTemplate.convertAndSend("entityChangeQueue", message);
    }
}
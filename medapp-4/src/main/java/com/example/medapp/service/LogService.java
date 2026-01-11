package com.example.medapp.service;

import com.example.medapp.model.Log;
import com.example.medapp.repository.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LogService {

    @Autowired
    private LogRepository logRepository;

    @Transactional
    public void logChange(String entityName, String entityId, String changeType, String changeDetails) {
        Log log = new Log(entityName, entityId, changeType, changeDetails);
        System.out.println("Saving log: " + log);
        logRepository.save(log);
    }

    @Transactional(readOnly = true)
    public Iterable<Log> getAllLogs() {
        return logRepository.findAll();
    }
}
package com.example.medapp.controller;

import com.example.medapp.model.Doctor;
import com.example.medapp.model.ShiftReport;
import com.example.medapp.service.DoctorService;
import com.example.medapp.service.ShiftReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/shiftReports")
public class ShiftReportController {

    private final ShiftReportService shiftReportService;
    private final DoctorService doctorService;

    @Autowired
    public ShiftReportController(ShiftReportService shiftReportService, DoctorService doctorService) {
        this.shiftReportService = shiftReportService;
        this.doctorService = doctorService;
    }

    @GetMapping
    public String listShiftReports(Model model) {
        List<ShiftReport> shiftReports = shiftReportService.getAllShiftReports();
        model.addAttribute("shiftReports", shiftReports);
        return "shiftReport/list";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("shiftReport", new ShiftReport());
        model.addAttribute("doctors", doctorService.getAllDoctors()); 
        return "shiftReport/create";
    }

    @PostMapping("/create")
    public String createShiftReport(@ModelAttribute ShiftReport shiftReport,
                                    @RequestParam("doctorId") Integer doctorId, 
                                    RedirectAttributes redirectAttributes) {
        Doctor doctor = doctorService.getDoctorById(doctorId);
        if (doctor != null) {
            shiftReport.setDoctor(doctor);
            shiftReport.setReportDate(new Date()); 
            shiftReportService.addShiftReport(shiftReport);
            redirectAttributes.addFlashAttribute("message", "Отчет успешно добавлен!");
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Доктор не найден!");
        }
        return "redirect:/shiftReports";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Integer id, Model model) {
        ShiftReport shiftReport = shiftReportService.getShiftReportById(id);
        if (shiftReport == null) {
            return "redirect:/shiftReports";
        }
        model.addAttribute("shiftReport", shiftReport);
        model.addAttribute("doctors", doctorService.getAllDoctors());
        model.addAttribute("selectedDoctorId", shiftReport.getDoctor().getId()); 
        return "shiftReport/edit";
    }

    @PostMapping("/edit/{id}")
    public String updateShiftReport(@PathVariable Integer id,
                                    @ModelAttribute ShiftReport shiftReport,
                                    @RequestParam("doctorId") Integer doctorId,
                                    RedirectAttributes redirectAttributes) {
        Doctor doctor = doctorService.getDoctorById(doctorId);
        if (doctor != null) {
            shiftReport.setId(id); 
            shiftReport.setDoctor(doctor);
            shiftReport.setReportDate(new Date());
            shiftReportService.updateShiftReport(shiftReport);
            redirectAttributes.addFlashAttribute("message", "Отчет успешно обновлен!");
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Доктор не найден!");
        }
        return "redirect:/shiftReports";
    }

    @PostMapping("/delete/{id}")
    public String deleteShiftReport(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        shiftReportService.deleteShiftReport(id);
        redirectAttributes.addFlashAttribute("message", "Отчет успешно удален!");
        return "redirect:/shiftReports";
    }


}

package com.example.medapp.controller;

import com.example.medapp.model.Doctor;
import com.example.medapp.model.ShiftReport;
import com.example.medapp.service.DoctorService;
import com.example.medapp.service.ShiftReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model; // Используется для передачи данных в представление
import org.springframework.web.bind.annotation.*; // Аннотации для маппинга запросов
import org.springframework.web.servlet.mvc.support.RedirectAttributes; // Для передачи сообщений после редиректа

import java.util.List;

@Controller
@RequestMapping("/doctors")
public class DoctorController {

    private final DoctorService doctorService;
    private final ShiftReportService shiftReportService; 

    @Autowired
    public DoctorController(DoctorService doctorService, ShiftReportService shiftReportService) {
        this.doctorService = doctorService;
        this.shiftReportService = shiftReportService;
    }

    @GetMapping
    public String listDoctors(Model model) {
        List<Doctor> doctors = doctorService.getAllDoctors();
        model.addAttribute("doctors", doctors);
        model.addAttribute("shiftReportService", shiftReportService);
        model.addAttribute("reportContentFormatter", new ReportContentFormatter());
        return "doctor/list";
    }


    @GetMapping("/create") 
    public String showCreateForm(Model model) {
        model.addAttribute("doctor", new Doctor()); 
        return "doctor/create";
    }


    @PostMapping("/create") 
    public String createDoctor(@ModelAttribute Doctor doctor, RedirectAttributes redirectAttributes) {
        doctorService.addDoctor(doctor);
        redirectAttributes.addFlashAttribute("message", "Доктор успешно добавлен!"); 
        return "redirect:/doctors"; 
    }

    @GetMapping("/edit/{id}") 
    public String showEditForm(@PathVariable Integer id, Model model) {
        Doctor doctor = doctorService.getDoctorById(id);
        if (doctor == null) {            
            return "redirect:/doctors";
        }
        model.addAttribute("doctor", doctor);
        return "doctor/edit";
    }

    @PostMapping("/edit/{id}") 
    public String updateDoctor(@PathVariable Integer id, @ModelAttribute Doctor doctor, RedirectAttributes redirectAttributes) {
        doctor.setId(id); 
        doctorService.updateDoctor(doctor);
        redirectAttributes.addFlashAttribute("message", "Доктор успешно обновлен!");
        return "redirect:/doctors";
    }

    @PostMapping("/delete/{id}") 
    public String deleteDoctor(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        doctorService.deleteDoctor(id);
        redirectAttributes.addFlashAttribute("message", "Доктор успешно удален!");
        return "redirect:/doctors";
    }

    public static class ReportContentFormatter {
        public String getShortReportContent(ShiftReport report, int maxLength) {
            if (report == null || report.getNotes() == null || report.getNotes().isEmpty()) {
                return "";
            }
            String fullContent = report.getNotes();
            if (fullContent.length() <= maxLength) {
                return fullContent;
            }
            return fullContent.substring(0, maxLength) + "...";
        }
    }
}

package com.expense.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.expense.entity.Report;
import com.expense.service.ReportService;

@RestController
@RequestMapping("/reports")
public class ReportController {

	@Autowired
	private ReportService reportService;
	
	@PostMapping
    public Report createUser(@RequestBody Report report) {
        return reportService.saveReport(report);
    }

    @GetMapping
    public List<Report> getAllReports() {
        return reportService.getAllReports();
    }

    @GetMapping("/{id}")
    public Optional<Report> getReportById(@PathVariable Long id) {
        return reportService.getReportById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteReport(@PathVariable Long id) {
    	reportService.deleteReport(id);
    }
}

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

import com.expense.dto.ExpenseDto;
import com.expense.dto.ReportDto;
import com.expense.entity.Expense;
import com.expense.entity.Report;
import com.expense.service.ReportService;

@RestController
@RequestMapping("/reports")
public class ReportController {

	@Autowired
	private ReportService reportService;
	
	@PostMapping
    public Report createReport(@RequestBody ReportDto reportDTO) {
        return reportService.saveReport(reportDTO);
    }

	@PostMapping("/generate")
    public List<Expense> getExpensesByUserAndDateRange(@RequestBody ExpenseDto filterDTO) {
        return reportService.getExpensesByUserAndDateRange(filterDTO);
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
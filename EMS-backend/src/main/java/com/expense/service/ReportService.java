package com.expense.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.expense.entity.Report;
import com.expense.repository.ReportRepository;

@Service
public class ReportService {
	@Autowired
	private ReportRepository reportRepo;
	
	public Report saveReport(Report report) {
		return  reportRepo.save(report);
	}
	
	public List<Report> getAllReports() {
		return reportRepo.findAll();
	}
	
	public Optional<Report> getReportById(Long id) {
		return reportRepo.findById(id);
	}
	
	public void deleteReport(Long id) {
		reportRepo.deleteById(id);
	}

}

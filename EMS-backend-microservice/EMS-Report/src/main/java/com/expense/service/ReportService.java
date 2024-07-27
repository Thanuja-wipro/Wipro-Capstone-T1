package com.expense.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.expense.dto.ExpenseDto;
import com.expense.dto.ReportDto;
import com.expense.entity.Category;
import com.expense.entity.Expense;
import com.expense.entity.Report;
import com.expense.entity.User;
import com.expense.repository.CategoryRepository;
import com.expense.repository.ExpenseRepository;
import com.expense.repository.ReportRepository;
import com.expense.repository.UserRepository;

@Service
public class ReportService {
	@Autowired
	private ReportRepository reportRepo;
	
	@Autowired
	private UserRepository userRepo;	

	@Autowired
    private ExpenseRepository expenseRepository;
	
	
	public Report saveReport(ReportDto reportDTO) {
		
		User user = userRepo.findById(reportDTO.getUser()).orElseThrow(() -> new RuntimeException("User not found"));

        Report report = new Report();
        report.setUser(user);
        report.setPeriod(reportDTO.getPeriod());
        report.setTotalAmount(reportDTO.getTotalAmount());
        report.setGeneratedDate(reportDTO.getGeneratedDate());

        return reportRepo.save(report);
	}
	

 
    public List<Expense> getExpensesByUserAndDateRange(ExpenseDto filterDTO) {
        User user = userRepo.findById(filterDTO.getUser())
            .orElseThrow(() -> new RuntimeException("User not found"));

        Date startDate = filterDTO.getStartDate();
        Date endDate = filterDTO.getEndDate();

        return expenseRepository.findByUserAndDateBetween(user, startDate, endDate);
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

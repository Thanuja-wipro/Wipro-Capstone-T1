package com.expense.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.expense.entity.Report;

public interface ReportRepository  extends JpaRepository<Report, Long>{

}

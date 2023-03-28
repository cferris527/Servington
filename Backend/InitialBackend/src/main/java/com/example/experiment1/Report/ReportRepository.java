package com.example.experiment1.Report;

import com.example.experiment1.Report.Report;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ReportRepository {

    Report findById(int id);

    List<Report> findAll();
}

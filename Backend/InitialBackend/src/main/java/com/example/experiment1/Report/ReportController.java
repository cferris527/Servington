package com.example.experiment1.Report;


import com.example.experiment1.Organization.Organization;
import com.example.experiment1.Post.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import com.example.experiment1.Message;
@RestController
public class ReportController {

    @Autowired ReportRepository reportRepository;

    @GetMapping(path = "/listReports")
    List<Report> getAllReports(){
        return reportRepository.findAll();
    }


}

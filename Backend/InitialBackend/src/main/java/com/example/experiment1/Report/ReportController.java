package com.example.experiment1.Report;


import com.example.experiment1.Organization.Organization;
import com.example.experiment1.Post.Post;
import com.example.experiment1.Volunteer.Volunteer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import com.example.experiment1.Message;
@RestController
public class ReportController {

    @Autowired ReportRepository reportRepository;

    @GetMapping(path = "/listReports")
    public List<Report> getAllReports() {
        return reportRepository.findAll();
    }

    @PostMapping(path = "/deleteReport/{reportId}")
    public Message deleteReport(@PathVariable int reportId){
        Message m = new Message();
        if(reportRepository.existsById((long)reportId)){
            reportRepository.deleteById(reportId);
            m.message = "success";
            return m;
        }
        m.message = "Deletion failed, Check if reportID exists";
        return m;
    }

    @PostMapping(path = "/createReport")
    Message createReport(@RequestBody Report r) {
        if (r == null) {
            Message m = new Message();
            m.message = "failed";
            return m;
        }
        reportRepository.save(r);
        Message m = new Message();
        m.message = "success";
        return m;
    }

    @GetMapping(path = "/getReport/{reportId}")
    Report getReport(@PathVariable int reportId) {
        Report r = new Report();
        if(reportRepository.existsById((long)reportId)){
            r = reportRepository.findById(reportId);
        }
        return r;
    }
}

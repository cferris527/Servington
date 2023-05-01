package com.example.experiment1.Report;


import com.example.experiment1.Organization.Organization;
import com.example.experiment1.Post.Post;
import com.example.experiment1.Post.PostRepository;
import com.example.experiment1.Team.Team;
import com.example.experiment1.Volunteer.Volunteer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.ArrayList;
import java.util.List;

import com.example.experiment1.Message;
@RestController
public class ReportController {

    @Autowired ReportRepository reportRepository;

    @Autowired PostRepository postRepository;

    @Operation(summary = "Lists all Reports", description = "Return a JSON array of all of the reports in the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully returned all posts.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Report.class)))
    })
    @GetMapping(path = "/listReports")
    public List<Report> getAllReports() {
        return reportRepository.findAll();
    }

    @Operation(summary = "Delete Report", description = "Deletes a report given the report title in the path")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully deleted report.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Report.class)))
    })
    @PostMapping(path = "/deleteReport/{reportTitle}")
    public Message deleteReport(@PathVariable String reportTitle){
        Message m = new Message();
        int id = 0;
        List<Report> reports = reportRepository.findAll();
        for(int i = 0; i < reports.toArray().length; i++){
            if(reports.get(i).getTitle().equals(reportTitle)){
                id = reports.get(i).getId();
                reportRepository.deleteById(id);
                m.message = "success";
                return m;
            }
        }
        m.message = "Deletion failed, Check if reportID exists";
        return m;
    }

    @Operation(summary = "Create Report", description = "Creates a report for the given post via title in the path given the response body provided with the request")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully created report.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Report.class)))
    })
    @PostMapping(path = "/createReport/{postTitle}")
    Message createReport(@RequestBody Report r, @PathVariable String postTitle) {
        if (r == null && !postRepository.existsById(postTitle)) {
            Message m = new Message();
            m.message = "failed";
            return m;
        }
        r.setReportPost(postRepository.findByTitle(postTitle));
        reportRepository.save(r);
        Message m = new Message();
        m.message = "success";
        return m;
    }

    @Operation(summary = "Gets Report by ID", description = "A report JSON object is returned given the team ID in the path")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully returned report", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Report.class)))
    })
    @GetMapping(path = "/getReport/{reportId}")
    Report getReport(@PathVariable int reportId) {
        Report r = new Report();
        if(reportRepository.existsById((long)reportId)){
            r = reportRepository.findById(reportId);
        }
        return r;
    }

    @Operation(summary = "Gets Post by Report ID", description = "A post JSON object is returned given the report ID in the path")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully returned post", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Report.class)))
    })
    @GetMapping(path = "/getPostFromReport/{reportId}")
    Post getPostFromReport(@PathVariable int reportId){
        Post post;
        Report r = new Report();
        if(reportRepository.existsById((long)reportId)){
            r = reportRepository.findById(reportId);
        }
        post = r.getReportPost();
        return post;
    }
}

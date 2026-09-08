package com.example.exersicevalidationprojecttracker.Controller;

import com.example.exersicevalidationprojecttracker.Model.ProjectTrackerValidation;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("api/v1/tracker")
public class ValidationController {

    ArrayList<ProjectTrackerValidation> projects = new ArrayList<>();
    ArrayList<ProjectTrackerValidation> sameCompanyPro = new ArrayList<>();

    @PostMapping("/add")
    public ResponseEntity<?> addProject(@RequestBody @Valid ProjectTrackerValidation newProject, Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        projects.add(newProject);
        return ResponseEntity.status(200).body("Project Added Successfully");
    }

    @GetMapping("/get")
    public ResponseEntity<?> getProjects(){
        return ResponseEntity.status(200).body(projects);
    }

    @PutMapping("/update/{index}")
    public ResponseEntity updateProject(@PathVariable int index, @RequestBody @Valid ProjectTrackerValidation newProject, Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        if(index >= projects.size() || index < 0 ){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        projects.set(index, newProject);
        return ResponseEntity.status(200).body("Project Updated Successfully ");
    }

    @DeleteMapping("/delete/{index}")
    public ResponseEntity<?> deleteProject(@PathVariable int index){
        if(index >= projects.size() || index < 0){
            return ResponseEntity.status(400).body("choose a correct index");
        }
        projects.remove(index);
        return ResponseEntity.status(200).body("project has been deleted ");
    }
    
    @PutMapping("changeStatus/{id}")
    public ResponseEntity<?> updateStatus(@PathVariable String id, Errors errors){
        if (errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        for(int i =0 ; i <projects.size() ; i++){
            if(projects.get(i).getId().equals(id)){
                String status = projects.get(i).getStatus();
                
                if(status.equalsIgnoreCase("not started")){
                    projects.get(i).setStatus("In Progress");
                } else if (status.equalsIgnoreCase("In Progress")) {
                    projects.get(i).setStatus("Completed");
                }else {
                    projects.get(i).setStatus("Not Started");
                }
                return ResponseEntity.status(200).body("Status have been change Successfully !!");

            }
        } return ResponseEntity.status(400).body("Project not found?!!");
    }

    @GetMapping("/get/{title}")
    public ResponseEntity<?> getByTitle(@PathVariable String title){

        for (int i =0 ; i <= projects.size() ; i++){
            if(projects.get(i).getTitle().equalsIgnoreCase(title)){
                return ResponseEntity.status(200).body(projects.get(i));
            }
        } return ResponseEntity.status(400).body("project not found");
    }

    @GetMapping("/get/sameCompany/{companyName}")
    public ResponseEntity<?> getSameCompanyName(@PathVariable String companyName){

        boolean found = false;

        for(int i = 0; i < projects.size(); i++){
            if(projects.get(i).getCompanyName().equalsIgnoreCase(companyName)){
                sameCompanyPro.add(projects.get(i));
                found = true;
            }
        }
        if(!found){
            return ResponseEntity.status(400).body("company has no Project's -_-");
        }
        return ResponseEntity.status(200).body(sameCompanyPro);
    }





}

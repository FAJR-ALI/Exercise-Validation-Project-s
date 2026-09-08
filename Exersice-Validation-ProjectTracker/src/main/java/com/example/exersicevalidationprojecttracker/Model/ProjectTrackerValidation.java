package com.example.exersicevalidationprojecttracker.Model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProjectTrackerValidation {

    @NotEmpty
    @Size(min = 2 , max = 5, message = "id must be around 2 to 5 char")
    private String id;

    @NotEmpty
    @Size(min = 9, max = 25, message = "title must be around 9 to 25 char")
    private String title;

    @NotEmpty
    @Size(min = 16, max = 200, message = "Description must be around 16 to 200 char")
    private String description;

    @NotEmpty
    @Pattern(regexp = "^(Not Started|In Progress|Completed)$", message = "Status must be: Not Started or In Progress or Completed")
    private String status;

    @NotEmpty
    @Size(min = 7, max = 20, message = "company name must be around 7 to 20 char")
    private String companyName;


}

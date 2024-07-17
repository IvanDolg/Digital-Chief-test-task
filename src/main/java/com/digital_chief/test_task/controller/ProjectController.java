package com.digital_chief.test_task.controller;

import com.digital_chief.test_task.dto.ProjectDto;
import com.digital_chief.test_task.service.ProjectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "Project Controller",
        description = "Project controller exposes rest apis"
)
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/projects")
public class ProjectController {
    private ProjectService projectService;

    @Operation(
            summary = "Create new project",
            description = "Used to save new project to a database"
    )
    @ApiResponses(
            @ApiResponse(
                    responseCode = "201",
                    description = "Project created"
            )
    )
    @PostMapping
    public ResponseEntity<ProjectDto> createProject(@RequestBody ProjectDto projectDto) {
        ProjectDto savedProject = projectService.save(projectDto);
        return new ResponseEntity<>(savedProject, HttpStatus.CREATED);
    }

    @Operation(
            summary = "Update project by id",
            description = "Used to update project from db by id"
    )
    @ApiResponses(
            @ApiResponse(
                    responseCode = "200",
                    description = "Project found"
            )
    )
    @PutMapping("/{id}")
    public ResponseEntity<ProjectDto> updateProject(@RequestBody ProjectDto projectDto,
                                                    @PathVariable Long id) {
        ProjectDto updatedProject = projectService.update(projectDto, id);
        return new ResponseEntity<>(updatedProject, HttpStatus.OK);
    }

    @Operation(
            summary = "Get all projects",
            description = "Used to retrieve all projects from db"
    )
    @ApiResponses(
            @ApiResponse(
                    responseCode = "200",
                    description = "Projects found"
            )
    )
    @GetMapping
    public ResponseEntity<List<ProjectDto>> getAllProjects() {
        List<ProjectDto> projects = projectService.findAll();
        return new ResponseEntity<>(projects, HttpStatus.OK);
    }

    @Operation(
            summary = "Get project by id",
            description = "Used to retrieve project from db by id"
    )
    @ApiResponses(
            @ApiResponse(
                    responseCode = "200",
                    description = "Project found"
            )
    )
    @GetMapping("/{id}")
    public ResponseEntity<ProjectDto> getProjectById(@PathVariable Long id) {
        ProjectDto project = projectService.findById(id);
        return new ResponseEntity<>(project, HttpStatus.OK);
    }

    @Operation(
            summary = "Delete project by id",
            description = "Used to delete project from db by id"
    )
    @ApiResponses(
            @ApiResponse(
                    responseCode = "200",
                    description = "Project deleted"
            )
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long id) {
        projectService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

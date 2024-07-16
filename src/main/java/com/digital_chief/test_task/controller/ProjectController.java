package com.digital_chief.test_task.controller;

import com.digital_chief.test_task.service.ProjectService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/projects")
public class ProjectController {
    private ProjectService projectService;
}

package com.digital_chief.test_task.service.impl;

import com.digital_chief.test_task.dto.ProjectDto;
import com.digital_chief.test_task.entity.Project;
import com.digital_chief.test_task.exception.ProjectAlreadyExistsException;
import com.digital_chief.test_task.exception.RecurseNotFoundException;
import com.digital_chief.test_task.mapper.AutoProjectMapper;
import com.digital_chief.test_task.repository.ProjectRepository;
import com.digital_chief.test_task.service.ProjectService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProjectServiceImpl implements ProjectService {
    private ProjectRepository projectRepository;
    @Override
    public ProjectDto save(ProjectDto projectDto) {
        Optional<Project> optionalProject = projectRepository.findByTitle(projectDto.getTitle());
        if (optionalProject.isPresent()){
            throw new ProjectAlreadyExistsException("Project already exists");
        }

        Project project = AutoProjectMapper.MAPPER.mapToProject(projectDto);
        Project savedProject = projectRepository.save(project);
        return AutoProjectMapper.MAPPER.mapToProjectDto(savedProject);
    }

    @Override
    public ProjectDto update(ProjectDto projectDto, Long id) {
        Project project = projectRepository.findById(id).orElseThrow(
                () -> new RecurseNotFoundException("Project", "id", String.valueOf(id))
        );

        project.setTitle(projectDto.getTitle());
        project.setDescription(projectDto.getDescription());

        Project updateUser = projectRepository.save(project);
        return AutoProjectMapper.MAPPER.mapToProjectDto(updateUser);
    }

    @Override
    public List<ProjectDto> findAll() {
        return projectRepository.findAll()
                .stream()
                .map(AutoProjectMapper.MAPPER::mapToProjectDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProjectDto findById(Long id) {
        Project project = projectRepository.findById(id).orElseThrow(
                () -> new RecurseNotFoundException("Project", "id", String.valueOf(id))
        );
        return AutoProjectMapper.MAPPER.mapToProjectDto(project);
    }

    @Override
    public void deleteById(Long id) {
        Project project = projectRepository.findById(id).orElseThrow(
                () -> new RecurseNotFoundException("Project", "id", String.valueOf(id))
        );
        projectRepository.delete(project);
    }
}

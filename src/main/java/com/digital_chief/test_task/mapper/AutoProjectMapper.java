package com.digital_chief.test_task.mapper;

import com.digital_chief.test_task.dto.ProjectDto;
import com.digital_chief.test_task.entity.Project;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AutoProjectMapper {
    AutoProjectMapper MAPPER = Mappers.getMapper(AutoProjectMapper.class);

    ProjectDto mapToProjectDto(Project project);
    Project mapToProject(ProjectDto projectDto);
}
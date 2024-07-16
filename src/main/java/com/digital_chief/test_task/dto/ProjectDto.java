package com.digital_chief.test_task.dto;

import com.digital_chief.test_task.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDto {
    private Long id;
    private String title;
    private String description;
    private User user;
}

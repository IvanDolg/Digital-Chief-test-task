package com.digital_chief.test_task.dto;

import com.digital_chief.test_task.entity.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(
        description = "Response project dto model information"
)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDto {
    @Schema(
            description = "Unique identifier of the project"
    )
    private Long id;

    @Schema(
            description = "Title of the project"
    )
    private String title;

    @Schema(
            description = "Description of the project"
    )
    private String description;

    @Schema(
            description = "User of the project"
    )
    private User user;
}

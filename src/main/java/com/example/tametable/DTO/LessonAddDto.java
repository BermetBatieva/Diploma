package com.example.tametable.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LessonAddDto {

    private Integer disciplineId;

    private Integer weekId;

    private Long timeLessonId;

    private Boolean isLecture;

    private Boolean weekTypeChislitel;

    private Boolean weekTypeZnamenatel;

    private Integer groupId;

    private String link;
}

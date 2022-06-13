package com.example.tametable.DTO;

import com.example.tametable.enums.WeekType;
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

    private WeekType weekType;

    private Integer groupId;
}

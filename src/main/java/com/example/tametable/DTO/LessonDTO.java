package com.example.tametable.DTO;

import com.example.tametable.entity.*;
import com.example.tametable.enums.WeekType;
import lombok.Data;

@Data
public class LessonDTO {
    private Long id;
    private Discipline discipline;
    private WeekDay weekDay;
    private User user;
    private TimeLesson timeLesson;
    private Boolean isLection;
    private WeekType weekType;
}

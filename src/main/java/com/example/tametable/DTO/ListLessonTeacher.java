package com.example.tametable.DTO;

import com.example.tametable.enums.WeekType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ListLessonTeacher {

    private String discipline;

    private String weekDay;

    private String timeLesson;

    private Boolean isLektion;

    private WeekType weekType;

    private String group;

}

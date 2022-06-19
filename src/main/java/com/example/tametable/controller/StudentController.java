package com.example.tametable.controller;


import com.example.tametable.security.UserPrincipal;
import com.example.tametable.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/student")
public class StudentController {
    private final WeekDayService weekDayService;
    private final TimeLessonsService timeLessonsService;
    private final GroupService groupService;
    private final DisciplineService disciplineService;
    private final LessonService lessonService;

    @GetMapping("/lessons")
    public String getLessons(@AuthenticationPrincipal UserPrincipal userPrincipal) {

        return "studentLessons";
    }
}

package com.example.tametable.controller;

import com.example.tametable.DTO.LessonAddDto;
import com.example.tametable.security.UserPrincipal;
import com.example.tametable.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/teacher")
public class TeacherController {
    private final WeekDayService weekDayService;
    private final TimeLessonsService timeLessonsService;
    private final GroupService groupService;
    private final DisciplineService disciplineService;
    private final LessonService lessonService;

    @GetMapping("/lessons")
    public String getLessons(Model model) {
        model.addAttribute("lessons", lessonService.getAllTeacherLessons());
        return "lessons";
    }

    @GetMapping("/lesson/create")
    public String createLesson(@AuthenticationPrincipal UserPrincipal userPrincipal, Model model) {
        model.addAttribute("weekDays", weekDayService.findWeekDayAll());
        model.addAttribute("timeLessons", timeLessonsService.findExerciseTimeAll());
        model.addAttribute("groups", groupService.findAllGroups());
        model.addAttribute("user", userPrincipal.getUser());
//        model.addAttribute("weekTypes", WeekType.values());
        model.addAttribute("disciplines", disciplineService.findDisciplineAll());
        return "createLesson";
    }

    @PostMapping("/lesson/create")
    public String createLesson(LessonAddDto lessonAddDto) {
        lessonService.createLesson(lessonAddDto);
        return "lessons";
    }
}

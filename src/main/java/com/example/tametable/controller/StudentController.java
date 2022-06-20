package com.example.tametable.controller;


import com.example.tametable.entity.Group;
import com.example.tametable.entity.Lesson;
import com.example.tametable.entity.Permission;
import com.example.tametable.security.UserPrincipal;
import com.example.tametable.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

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
    public String getLessons(@AuthenticationPrincipal UserPrincipal userPrincipal, Model model) {
        model.addAttribute("user", userPrincipal.getUser());
        Set<Group> groups = lessonService.findAllByUser(userPrincipal.getUser()).stream().map(Lesson::getGroup).collect(Collectors.toSet());
        model.addAttribute("groups", groups);
        model.addAttribute("permission", Permission.ROLE_STUDENT);
        model.addAttribute("date", LocalDate.now());
        return "studentLessons";
    }
}

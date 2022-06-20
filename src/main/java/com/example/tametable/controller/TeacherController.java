package com.example.tametable.controller;

import com.example.tametable.DTO.LessonAddDto;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

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
    public String getLessons(Model model, @AuthenticationPrincipal UserPrincipal userPrincipal) {
        model.addAttribute("user", userPrincipal.getUser());
        Set<Group> groups = lessonService.findAllByUser(userPrincipal.getUser()).stream().map(Lesson::getGroup).collect(Collectors.toSet());
        model.addAttribute("groups", groups);
        model.addAttribute("permission", Permission.ACTION_LESSONS);
        model.addAttribute("date", LocalDate.now());
        return "lessons";
    }

    @GetMapping("/lesson/create")
    public String createLesson(@AuthenticationPrincipal UserPrincipal userPrincipal, Model model) {
        model.addAttribute("weekDays", weekDayService.findWeekDayAll());
        model.addAttribute("timeLessons", timeLessonsService.findExerciseTimeAll());
        model.addAttribute("groups", groupService.findAllGroups());
        model.addAttribute("user", userPrincipal.getUser());
        model.addAttribute("disciplines", disciplineService.findDisciplineAll());
        return "createLesson";
    }

    @PostMapping("/lesson/create")
    public String createLesson(LessonAddDto lessonAddDto) throws Exception {
        lessonService.createLesson(lessonAddDto);
        return "redirect:/teacher/lessons";
    }

    @GetMapping("/lesson/update/{id}")
    public String updateLesson(@PathVariable(name = "id") Lesson lesson, Model model, @AuthenticationPrincipal UserPrincipal userPrincipal) {
        model.addAttribute("lesson", lesson);
        model.addAttribute("weekDays", weekDayService.findWeekDayAll());
        model.addAttribute("timeLessons", timeLessonsService.findExerciseTimeAll());
        model.addAttribute("groups", groupService.findAllGroups());
        model.addAttribute("disciplines", disciplineService.findDisciplineAll());
        model.addAttribute("user", userPrincipal.getUser());
        return "changeLesson";
    }

    @PostMapping("/lesson/update/{id}")
    public String updateLesson(@PathVariable Long id, LessonAddDto lessonAddDto) {
        lessonService.updateLesson(id, lessonAddDto);
        return "redirect:/teacher/lessons";
    }
}

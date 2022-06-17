package com.example.tametable.controller.restController;

import com.example.tametable.DTO.ListLessonGroup;
import com.example.tametable.DTO.ListLessonTeacher;
import com.example.tametable.entity.Group;
import com.example.tametable.entity.Lesson;
import com.example.tametable.security.UserPrincipal;
import com.example.tametable.service.GroupService;
import com.example.tametable.service.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class GroupRestController {
    private final GroupService groupService;

    private final LessonService lessonService;

    @GetMapping("/groups")
    public ResponseEntity<List<Group>> getAllGroups() {
        return new ResponseEntity<>(groupService.findAllGroups(), HttpStatus.OK);
    }

    @DeleteMapping("/groups/{id}")
    public ResponseEntity<Void> deleteGroup(@PathVariable int id) {
        groupService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/all-lesson-by-group/{groupId}")
    public List<ListLessonGroup> getLessonsByGroupId(@PathVariable Integer groupId) {
        return lessonService.getAllLessonsByGroupId(groupId);
    }

    @GetMapping("/all-lesson-teacher")
    public List<ListLessonTeacher> getLessonByTeacher(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        return lessonService.getAllTeacherLessons(userPrincipal.getUser());
    }

    @GetMapping("/all-lessons-by-week/{weekId}")
    public List<ListLessonGroup> getAllLessonsByWeekId(@PathVariable Integer weekId){
        return lessonService.getAllLessonsByWeekId(weekId);
    }
}

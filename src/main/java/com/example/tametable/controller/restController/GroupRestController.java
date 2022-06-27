package com.example.tametable.controller.restController;

import com.example.tametable.DTO.ListLessonGroup;
import com.example.tametable.DTO.ListLessonTeacher;
import com.example.tametable.entity.Group;
import com.example.tametable.security.UserPrincipal;
import com.example.tametable.service.GroupService;
import com.example.tametable.service.LessonService;
import com.example.tametable.service.TimeLessonsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class GroupRestController {
    private final GroupService groupService;

    private final LessonService lessonService;

    private final TimeLessonsService timeLessonsService;

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
    public List<ListLessonGroup> getAllLessonsByWeekId(@AuthenticationPrincipal UserPrincipal userPrincipal, @PathVariable Integer weekId) {
        if (userPrincipal.getUser().getRole().name().equals("ADMIN")) {
            return lessonService.getAllLessonsByWeekId(weekId);
        } else {
            return lessonService.getAllLessonsByUserGroupIdAndWeekId(userPrincipal.getUser(), weekId);
        }

    }

    @GetMapping("/all-lesson-by-week-groupId/{weekId}/{groupId}")
    public List<ListLessonGroup> getAllLessonsByWeekIdAndGroupId(@PathVariable int weekId, @PathVariable int groupId) {
        List<ListLessonGroup> list = lessonService.getAllLessonsByGroupIdAndWeekId(groupId, weekId);
        List<ListLessonGroup> newList = new ArrayList<>();
        int min = list.stream().min(Comparator.comparing(ListLessonGroup::getNumTimeLesson)).get().getNumTimeLesson();
        int max = list.stream().max(Comparator.comparing(ListLessonGroup::getNumTimeLesson)).get().getNumTimeLesson();
        for (int i = 1; i <= 8; i++) {
//            if (i <= 3) {
//                continue;
//            }
            if (i > max && i >= 4) {
                break;
            }
            int k = i;
            Optional<ListLessonGroup> list2 = list.stream().filter(listLessonGroup -> listLessonGroup.getNumTimeLesson() == k).findFirst();
            if (list2.isPresent()) {
                List<ListLessonGroup> list1 = list.stream().filter(listLessonGroup -> listLessonGroup.getNumTimeLesson() == k).collect(Collectors.toList());
                for (int j = 0; j < list1.size(); j++) {
                    newList.add(list1.get(j));
                }
            } else if (!list2.isPresent() && i <= 3 && min != 1 && max != 3 && i != 3 || min == max && i <= 3 || i == 1 && !list2.isPresent() || min > 3 && i <= 3) {
                ListLessonGroup listLessonGroup = new ListLessonGroup();
                listLessonGroup.setTimeLesson(timeLessonsService.findByNumberLesson(i).getTime());
                listLessonGroup.setIdLesson(-1L);
                newList.add(listLessonGroup);
            } else {
                ListLessonGroup listLessonGroup = new ListLessonGroup();
                listLessonGroup.setTimeLesson(timeLessonsService.findByNumberLesson(i).getTime());
                newList.add(listLessonGroup);
            }
        }
        return newList;
    }
}

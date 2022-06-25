package com.example.tametable.controller.restController;

import com.example.tametable.DTO.LessonAddAdminDto;
import com.example.tametable.DTO.LessonAddDto;
import com.example.tametable.service.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/lessons")
@RequiredArgsConstructor
public class LessonRestController {
    private final LessonService lessonService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createLesson(@RequestBody LessonAddDto lessonAddDto) {
        String msg = lessonService.createLesson(lessonAddDto);
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateLesson(@RequestBody LessonAddDto lessonAddDto, @PathVariable(name = "id") Long id) {
        lessonService.updateLesson(id, lessonAddDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/create-admin")
    public ResponseEntity<String> createLessonAdmin(@RequestBody LessonAddAdminDto dto){
        lessonService.createLessonFromAdmin(dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

package com.example.tametable.service;

import com.example.tametable.entity.TimeLesson;
import com.example.tametable.repository.TimeLessonRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TimeLessonsServiceImpl implements TimeLessonsService {
    private final TimeLessonRepo timeLessonRepo;


    @Override
    public List<TimeLesson> findExerciseTimeAll() {
        return timeLessonRepo.findAll();
    }
}

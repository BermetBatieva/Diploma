package com.example.tametable.service;

import com.example.tametable.entity.ExerciseTime;
import com.example.tametable.repository.ExerciseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExerciseTimeServiceImpl implements ExerciseTimeService {
    private final ExerciseRepository exerciseRepository;

    @Override
    public List<ExerciseTime> findExerciseTimeAll() {
        return exerciseRepository.findAll();
    }
}

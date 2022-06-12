package com.example.tametable.repository;

import com.example.tametable.entity.TimeLesson;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimeLessonRepo extends CrudRepository<TimeLesson, Long> {
}

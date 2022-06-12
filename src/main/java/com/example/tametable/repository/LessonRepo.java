package com.example.tametable.repository;

import com.example.tametable.entity.Lesson;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LessonRepo extends CrudRepository<Lesson, Long> {
}

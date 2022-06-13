package com.example.tametable.repository;

import com.example.tametable.entity.Lesson;
import com.example.tametable.entity.User;
import org.springframework.data.relational.core.sql.In;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LessonRepo extends CrudRepository<Lesson, Long> {

    boolean existsByUserAndWeekDay_IdAndTimeLesson_IdAndIsLection(User user,Integer weekDayId,Long timeLessonId,boolean isLection);

    boolean existsByGroup_IdAndTimeLesson_IdAndWeekDay_Id(Integer groupId,Long timeLessonId,Integer weekDayId);
}

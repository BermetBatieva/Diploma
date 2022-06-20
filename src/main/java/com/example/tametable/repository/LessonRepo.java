package com.example.tametable.repository;

import com.example.tametable.entity.Lesson;
import com.example.tametable.entity.User;
import com.example.tametable.enums.Status;
import org.springframework.data.relational.core.sql.In;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LessonRepo extends CrudRepository<Lesson, Long> {

    boolean existsByUserAndWeekDay_IdAndTimeLesson_IdAndStatusAndWeekTypeChislitelAndAndWeekTypeZnamenatelAndGroup_Id(User user, Integer weekDayId, Long timeLessonId, Status status,boolean weekTypeChislitel ,boolean weekTypeZnamenatel,Integer grId);

    boolean existsByUserAndWeekDay_IdAndTimeLesson_IdAndIsLectionAndStatus(User user, Integer weekDayId, Long timeLessonId, boolean isLection, Status status);


    boolean existsByGroup_IdAndTimeLesson_IdAndWeekDay_IdAndStatus(Integer groupId,Long timeLessonId,Integer weekDayId,Status status);

    List<Lesson> findByUserAndStatus(User user,Status status);

    List<Lesson>  findByStatusAndGroup_Id(Status status, Integer groupId);

    List<Lesson> findByStatusAndGroup_IdAndWeekDay_Id(Status status,Integer groupId, Integer weekDayId);

    List<Lesson> findByStatusAndWeekDay_Id(Status status,Integer weekDayId);
}

package com.example.tametable.service;

import com.example.tametable.DTO.LessonAddDto;
import com.example.tametable.DTO.LessonDTO;
import com.example.tametable.entity.Lesson;
import com.example.tametable.entity.User;
import com.example.tametable.exception.EntityNotFoundException;
import com.example.tametable.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LessonService {
    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private TimeLessonRepo timeLessonRepo;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private DisciplineRepository disciplineRepository;

    @Autowired
    private WeekDayRepository weekDayRepository;

    @Autowired
    private LessonRepo lessonRepo;


    public Lesson update(LessonDTO lessonDTO, Long id) {
        Lesson lesson = lessonRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Could not found staff: ", id));
        lesson.setDiscipline(lessonDTO.getDiscipline());
        lesson.setWeekDay(lessonDTO.getWeekDay());
        lesson.setUser(lessonDTO.getUser());
        lesson.setTimeLesson(lessonDTO.getTimeLesson());
        lesson.setIsLection(lessonDTO.getIsLection());
        lesson.setWeekType(lessonDTO.getWeekType());
        return lessonRepo.save(lesson);
    }

    public Long delete(Long id) throws EntityNotFoundException {
        if(!lessonRepo.existsById(id)) {
            throw new EntityNotFoundException("Could not found staff: ", id);
        }
        lessonRepo.deleteById(id);
        return id;
    }


    public void createLesson(LessonAddDto lessonAdd){
        User user = userService.getUser();
        if(!lessonRepo.existsByUserAndWeekDay_IdAndTimeLesson_IdAndIsLection(
                user,lessonAdd.getWeekId(),lessonAdd.getTimeLessonId(),false)) {
            if(!lessonRepo.existsByGroup_IdAndTimeLesson_IdAndWeekDay_Id(lessonAdd.getGroupId(),lessonAdd.getTimeLessonId(),lessonAdd.getWeekId())) {
                Lesson lesson = new Lesson();
                lesson.setUser(user);
                lesson.setTimeLesson(timeLessonRepo.findById(lessonAdd.getTimeLessonId()).orElse(null));
                lesson.setGroup(groupRepository.findById(lessonAdd.getGroupId()).orElse(null));

                lesson.setIsLection(lessonAdd.getIsLecture());
                lesson.setDiscipline(disciplineRepository.findById(lessonAdd.getDisciplineId()).orElse(null));
                lesson.setWeekDay(weekDayRepository.findById(lessonAdd.getWeekId()).orElse(null));
                lesson.setWeekType(lessonAdd.getWeekType());
                lessonRepo.save(lesson);
            }
        }
    }




}

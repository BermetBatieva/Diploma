package com.example.tametable.service;

import com.example.tametable.DTO.LessonDTO;
import com.example.tametable.entity.Lesson;
import com.example.tametable.exception.EntityNotFoundException;
import com.example.tametable.repository.DisciplineRepository;
import com.example.tametable.repository.LessonRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LessonService {

    private final LessonRepo lessonRepo;

    private final DisciplineRepository disciplineRepository;

    public void create(Lesson lesson) {
        lessonRepo.save(lesson);
    }

    public Lesson update(LessonDTO lessonDTO, Long id) {
        Lesson lesson = lessonRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Could not found staff: ", id));
        lesson.setDiscipline(lessonDTO.getDiscipline());
        lesson.setSchedule(lessonDTO.getSchedule());
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
}

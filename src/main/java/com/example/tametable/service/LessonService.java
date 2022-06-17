package com.example.tametable.service;

import com.example.tametable.DTO.LessonAddDto;
import com.example.tametable.DTO.ListLessonGroup;
import com.example.tametable.DTO.ListLessonTeacher;
import com.example.tametable.entity.Lesson;
import com.example.tametable.entity.Role;
import com.example.tametable.entity.User;
import com.example.tametable.enums.Status;
import com.example.tametable.exception.EntityNotFoundException;
import com.example.tametable.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

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


//    public void update(LessonAddDto lessonDTO, Long id) throws Exception {
//        Lesson lesson = lessonRepo.findById(id)
//                .orElseThrow(() -> new EntityNotFoundException("Could not found staff: ", id));
//        if (lesson.getUser() == userService.getUser() || userService.getUser().getRole() == Role.ADMIN) {
//            if (!lessonRepo.existsByUserAndWeekDay_IdAndTimeLesson_IdAndIsLectionAndStatus(
//                    userService.getUser(), lessonDTO.getWeekId(), lessonDTO.getTimeLessonId(), false, Status.ACTIVE)) {
//                if (!lessonRepo.existsByGroup_IdAndTimeLesson_IdAndWeekDay_IdAndStatusAndWeekTypeChislitelAndWeekTypeZnamenatel(lessonDTO.getGroupId(), lessonDTO.getTimeLessonId(), lessonDTO.getWeekId(), Status.ACTIVE)) {
//                    lesson.setTimeLesson(timeLessonRepo.findById(lessonDTO.getTimeLessonId()).orElse(null));
//                    lesson.setGroup(groupRepository.findById(lessonDTO.getGroupId()).orElse(null));
//                    lesson.setIsLection(lessonDTO.getIsLecture());
//                    lesson.setDiscipline(disciplineRepository.findById(lessonDTO.getDisciplineId()).orElse(null));
//                    lesson.setWeekDay(weekDayRepository.findById(lessonDTO.getWeekId()).orElse(null));
//                    lesson.setWeekTypeChislitel(lessonDTO.getWeekTypeChislitel());
//                    lesson.setLink(lessonDTO.getLink());
//                    lesson.setWeekTypeZnamenatel(lessonDTO.getWeekTypeZnamenatel());
//                    lessonRepo.save(lesson);
//                }
//            }
//        }
//        else
//            throw new Exception("Ошибка при изменении!");
//    }

    public Long delete(Long id) {
        Lesson lesson = lessonRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Could not found staff: ", id));
        if (lesson.getUser() == userService.getUser() || userService.getUser().getRole() == Role.ADMIN) {
            lesson.setStatus(Status.DELETE);
            return lessonRepo.save(lesson).getId();
        } else
            return -1L;
    }


    public void createLesson(LessonAddDto lessonAdd) {
        User user = userService.getUser();
        if (!lessonRepo.existsByUserAndWeekDay_IdAndTimeLesson_IdAndIsLectionAndStatus(
                user, lessonAdd.getWeekId(), lessonAdd.getTimeLessonId(), false, Status.ACTIVE)) {
            if (!lessonRepo.existsByGroup_IdAndTimeLesson_IdAndWeekDay_IdAndStatusAndWeekTypeChislitelAndWeekTypeZnamenatel
                    (lessonAdd.getGroupId(), lessonAdd.getTimeLessonId(), lessonAdd.getWeekId(), Status.ACTIVE, true, true)) {
                Lesson lesson = new Lesson();
                lesson.setUser(user);
                lesson.setStatus(Status.ACTIVE);
                lesson.setTimeLesson(timeLessonRepo.findById(lessonAdd.getTimeLessonId()).orElse(null));
                lesson.setGroup(groupRepository.findById(lessonAdd.getGroupId()).orElse(null));
                lesson.setIsLection(lessonAdd.getIsLecture());
                lesson.setDiscipline(disciplineRepository.findById(lessonAdd.getDisciplineId()).orElse(null));
                lesson.setWeekDay(weekDayRepository.findById(lessonAdd.getWeekId()).orElse(null));
                lesson.setWeekTypeChislitel(lessonAdd.getWeekTypeChislitel());
                lesson.setLink(lessonAdd.getLink());
                lesson.setWeekTypeZnamenatel(lessonAdd.getWeekTypeZnamenatel());
                lessonRepo.save(lesson);
            } else if (lessonRepo.existsByGroup_IdAndTimeLesson_IdAndWeekDay_IdAndStatusAndWeekTypeChislitelAndWeekTypeZnamenatel
                    (lessonAdd.getGroupId(), lessonAdd.getTimeLessonId(), lessonAdd.getWeekId(), Status.ACTIVE, false, true)) {
                Lesson lesson = new Lesson();
                lesson.setUser(user);
                lesson.setTimeLesson(timeLessonRepo.findById(lessonAdd.getTimeLessonId()).orElse(null));
                lesson.setGroup(groupRepository.findById(lessonAdd.getGroupId()).orElse(null));
                lesson.setIsLection(lessonAdd.getIsLecture());
                lesson.setDiscipline(disciplineRepository.findById(lessonAdd.getDisciplineId()).orElse(null));
                lesson.setWeekDay(weekDayRepository.findById(lessonAdd.getWeekId()).orElse(null));
                lesson.setWeekTypeChislitel(lessonAdd.getWeekTypeChislitel()); // тут только учит-ся числитель
                lesson.setLink(lessonAdd.getLink());
                lessonRepo.save(lesson);
            } else if (lessonRepo.existsByGroup_IdAndTimeLesson_IdAndWeekDay_IdAndStatusAndWeekTypeChislitelAndWeekTypeZnamenatel
                    (lessonAdd.getGroupId(), lessonAdd.getTimeLessonId(), lessonAdd.getWeekId(), Status.ACTIVE, true, false)) {
                Lesson lesson = new Lesson();
                lesson.setUser(user);
                lesson.setTimeLesson(timeLessonRepo.findById(lessonAdd.getTimeLessonId()).orElse(null));
                lesson.setGroup(groupRepository.findById(lessonAdd.getGroupId()).orElse(null));
                lesson.setIsLection(lessonAdd.getIsLecture());
                lesson.setDiscipline(disciplineRepository.findById(lessonAdd.getDisciplineId()).orElse(null));
                lesson.setWeekDay(weekDayRepository.findById(lessonAdd.getWeekId()).orElse(null));
                lesson.setWeekTypeZnamenatel(lessonAdd.getWeekTypeZnamenatel());
                lesson.setLink(lessonAdd.getLink());
                lessonRepo.save(lesson);
            }
        }
    }

    public List<ListLessonTeacher> getAllTeacherLessons(User user) {
        List<ListLessonTeacher> listModel = new ArrayList<>();
        List<Lesson> lessons = lessonRepo.findByUserAndStatus(user, Status.ACTIVE);
        for (Lesson lesson : lessons) {
            ListLessonTeacher listLessonTeacherModel = new ListLessonTeacher();

            listLessonTeacherModel.setTimeLesson(lesson.getTimeLesson().getTime());
            listLessonTeacherModel.setDiscipline(lesson.getDiscipline().getName());
            listLessonTeacherModel.setGroup(lesson.getGroup().getName());
            listLessonTeacherModel.setLink(lesson.getLink());
            listLessonTeacherModel.setWeekDay(lesson.getWeekDay().getName());
            listLessonTeacherModel.setWeekTypeChislitel(lesson.getWeekTypeChislitel());
            listLessonTeacherModel.setWeekTypeZnamenatel(lesson.getWeekTypeZnamenatel());
            listLessonTeacherModel.setIsLektion(lesson.getIsLection());
            listModel.add(listLessonTeacherModel);
        }
        return listModel;
    }

    public List<ListLessonGroup> getAllLessonsByGroupId(Integer groupId) {
        List<ListLessonGroup> listModel = new ArrayList<>();
        List<Lesson> lessonList = lessonRepo.findByStatusAndGroup_Id(Status.ACTIVE, groupId);

        for (Lesson lesson : lessonList) {

            ListLessonGroup model = new ListLessonGroup();

            model.setNumTimeLesson(lesson.getTimeLesson().getNumberLesson());
            model.setTimeLesson(lesson.getTimeLesson().getTime());
            model.setDiscipline(lesson.getDiscipline().getName());
            model.setGroup(lesson.getGroup().getName());
            model.setLink(lesson.getLink());
            model.setWeekDay(lesson.getWeekDay().getName());
            model.setWeekTypeChislitel(lesson.getWeekTypeChislitel());
            model.setWeekTypeZnamenatel(lesson.getWeekTypeZnamenatel());
            model.setIsLektion(lesson.getIsLection());
            model.setTeacher(lesson.getUser().getFirstName() + " " + lesson.getUser().getLastName());
            listModel.add(model);
        }
        listModel.sort(Comparator.comparingInt(ListLessonGroup::getNumTimeLesson));
        return listModel;

    }

    public List<ListLessonGroup> getAllLessonsByWeekId(Integer weekDayId){
        List<ListLessonGroup> listModel = new ArrayList<>();
        List<Lesson> lessons = lessonRepo.findByStatusAndWeekDay_Id(Status.ACTIVE,weekDayId);

        for (Lesson lesson : lessons){
            ListLessonGroup model = new ListLessonGroup();

            model.setNumTimeLesson(lesson.getTimeLesson().getNumberLesson());
            model.setTimeLesson(lesson.getTimeLesson().getTime());
            model.setDiscipline(lesson.getDiscipline().getName());
            model.setGroup(lesson.getGroup().getName());
            model.setLink(lesson.getLink());
            model.setWeekDay(lesson.getWeekDay().getName());
            model.setWeekTypeChislitel(lesson.getWeekTypeChislitel());
            model.setWeekTypeZnamenatel(lesson.getWeekTypeZnamenatel());
            model.setIsLektion(lesson.getIsLection());
            model.setTeacher(lesson.getUser().getFirstName() + " " + lesson.getUser().getLastName());

            listModel.add(model);
        }
        listModel.sort(Comparator.comparingInt(ListLessonGroup::getNumTimeLesson));
        return listModel;
    }

}

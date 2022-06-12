package com.example.tametable.repository;

import com.example.tametable.entity.Schedule;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleRepo extends CrudRepository<Schedule, Long> {
    Schedule findByUserId(Long id);
}

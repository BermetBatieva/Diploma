package com.example.tametable.repository;

import com.example.tametable.entity.ExerciseTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExerciseRepository extends JpaRepository<ExerciseTime, Integer> {
}

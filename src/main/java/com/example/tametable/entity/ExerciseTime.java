package com.example.tametable.entity;

import lombok.*;

import javax.persistence.*;

@Entity(name = "exercise_time")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExerciseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "exercise")
    private String exercise;

    @Column(name = "time")
    private String time;
}

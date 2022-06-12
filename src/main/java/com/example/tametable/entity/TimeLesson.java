package com.example.tametable.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "times")
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
public class TimeLesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String time;

    Integer numberLesson;
}

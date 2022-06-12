package com.example.tametable.DTO;

import com.example.tametable.entity.Group;
import com.example.tametable.entity.Schedule;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Builder
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ScheduleDTO {
    List<Group> groups;
    Schedule schedule;
    String permission;
}

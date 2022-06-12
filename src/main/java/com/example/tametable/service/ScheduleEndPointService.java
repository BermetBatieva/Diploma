package com.example.tametable.service;

import com.example.tametable.DTO.ScheduleDTO;
import com.example.tametable.entity.Permission;
import com.example.tametable.entity.User;
import com.example.tametable.repository.ScheduleRepo;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ScheduleEndPointService {

    @NonNull ScheduleRepo scheduleRepo;
    @NonNull UserService userService;

    public ScheduleDTO getByUser() {
        User user = userService.getUser();
        if (user.getRole().getPermission().contains(Permission.ROLE_ADMIN)) {
            return ScheduleDTO.builder()
                    .schedule(scheduleRepo.findByUserId(user.getId()))
                    .permission(user.getRole().name())
                    .groups(null)
                    .build();
        } else if (user.getRole().getPermission().contains(Permission.ROLE_STUDENT)) {

        }
        return null;
    }
}

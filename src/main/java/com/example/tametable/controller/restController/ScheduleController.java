package com.example.tametable.controller.restController;

import com.example.tametable.DTO.ScheduleDTO;
import com.example.tametable.service.ScheduleEndPointService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/schedule")
@RequiredArgsConstructor
@Slf4j
public class ScheduleController {

    @NonNull ScheduleEndPointService service;

    @GetMapping("/")
    public ScheduleDTO getScheduleByUser() {
        ScheduleDTO scheduleDTO = service.getByUser();
        return scheduleDTO;
    }

    @GetMapping("/group")
    public ScheduleDTO getScheduleByGroup(@RequestParam  Long groupId) {
        return null;
    }
}

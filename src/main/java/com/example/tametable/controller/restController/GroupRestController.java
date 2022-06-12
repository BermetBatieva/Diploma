package com.example.tametable.controller.restController;

import com.example.tametable.entity.Group;
import com.example.tametable.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class GroupRestController {
    private final GroupService groupService;

    @GetMapping("/groups")
    public ResponseEntity<List<Group>> getAllGroups() {
        return new ResponseEntity<>(groupService.findAllGroups(), HttpStatus.OK);
    }

    @DeleteMapping("/groups/{id}")
    public ResponseEntity<Void> deleteGroup(@PathVariable int id) {
        groupService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

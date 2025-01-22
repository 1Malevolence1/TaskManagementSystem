package com.example.TaskManagementSystem.task.controller;


import com.example.TaskManagementSystem.task.dto.TaskResponseDto;
import com.example.TaskManagementSystem.task.dto.TaskUserUpdateRequestDto;
import com.example.TaskManagementSystem.task.model.Priority;
import com.example.TaskManagementSystem.task.model.Status;
import com.example.TaskManagementSystem.task.serivce.TaskService;
import com.example.TaskManagementSystem.utils.BindingResultValidate;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/task")
@RequiredArgsConstructor
@Slf4j
public class TaskController {

    private final TaskService taskService;
    private final BindingResultValidate bindingResultValidate;


    @GetMapping("/{taskId:\\d+}")
    public ResponseEntity<TaskResponseDto> getTask(@PathVariable(name = "taskId") Long id){
        return ResponseEntity.ok(
                taskService.get(id)
        );
    }

    
    @PutMapping("/account/update")
    public ResponseEntity<Void> updateUserTask(@Valid @RequestBody TaskUserUpdateRequestDto dto, BindingResult bindingResult){
        log.info("start method updateUserTask. Dto: {}", dto);

        bindingResultValidate.check(bindingResult);;
        taskService.update(dto, null);

        log.info("task update");

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/get/all")
    public ResponseEntity<Page<TaskResponseDto>> getTaskById(
            @RequestParam(name = "accountId", required = false) Long id,
            @RequestParam(name = "status", required = false) Status status,
            @RequestParam(name = "priority", required = false)Priority priority,
            @RequestParam(name = "size", defaultValue = "10") Integer size,
            @RequestParam(name = "page", defaultValue = "1") Integer page){


        return ResponseEntity.ok(taskService.getAllTasksById(id, status, priority, size, page));
    }
}

package com.example.TaskManagementSystem.task.controller;


import com.example.TaskManagementSystem.task.dto.TaskResponseDto;
import com.example.TaskManagementSystem.task.dto.TaskUserUpdateRequestDto;
import com.example.TaskManagementSystem.task.serivce.TaskService;
import com.example.TaskManagementSystem.utils.BindingResultValidate;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    @GetMapping("/account/{accountId:\\d+}")
    public ResponseEntity<List<TaskResponseDto>> getTaskById(@PathVariable(name = "accountId") Long id){
        return ResponseEntity.ok(taskService.getAllTasksById(id));
    }
}

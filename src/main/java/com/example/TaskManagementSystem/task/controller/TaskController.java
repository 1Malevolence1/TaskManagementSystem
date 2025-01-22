package com.example.TaskManagementSystem.task.controller;


import com.example.TaskManagementSystem.task.dto.TaskAdminUpdateRequestDto;
import com.example.TaskManagementSystem.task.dto.TaskCreateRequestDto;
import com.example.TaskManagementSystem.task.dto.TaskResponseDto;
import com.example.TaskManagementSystem.task.serivce.TaskService;
import com.example.TaskManagementSystem.task.dto.TaskUserUpdateRequestDto;
import com.example.TaskManagementSystem.utils.BindingResultValidate;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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


    @PostMapping("/add")
    public ResponseEntity<Void> addTask(@Valid @RequestBody TaskCreateRequestDto dto, BindingResult bindingResult) throws BindException {
        log.info("start method by add Task. Dto: {}", dto);
            bindingResultValidate.check(bindingResult);
            taskService.create(dto);
            log.info("task add");
            return ResponseEntity.noContent().build();
        }



    @PutMapping("/admin/update")
    public ResponseEntity<Void> updateAdminTask(@RequestBody TaskAdminUpdateRequestDto dto){
        log.info("start method by updateAdminTask Task. Dto: {}", dto);

        taskService.update(dto, null);

        log.info("task update");
        return ResponseEntity.noContent().build();
    }

    // Ошибка с валидцация данных/ Ошибки на уровене Sql
    @PutMapping("/user/update")
    public ResponseEntity<Void> updateUserTask(@Valid @RequestBody TaskUserUpdateRequestDto dto, BindingResult bindingResult){
        log.info("start method updateUserTask. Dto: {}", dto);

        bindingResultValidate.check(bindingResult);;
        taskService.update(dto);

        log.info("task update");

        return ResponseEntity.noContent().build();
    }


    @DeleteMapping("/{taskId:\\d+}/delete")
    public ResponseEntity<Void> deleteTask(@PathVariable(name = "taskId") Long id){
        log.info("start method by delete Task with ID: {}", id);

        taskService.delete(id);

        log.info("task delete");
        return ResponseEntity.noContent().build();
    }


}

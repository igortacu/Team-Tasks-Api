package md.igor.teamtasks.controller;

import jakarta.validation.Valid;
import md.igor.teamtasks.dto.request.CreateTaskRequest;
import md.igor.teamtasks.dto.request.UpdateTaskRequest;
import md.igor.teamtasks.dto.response.TaskResponse;
import md.igor.teamtasks.entity.TaskPriority;
import md.igor.teamtasks.entity.TaskStatus;
import md.igor.teamtasks.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TaskResponse create(@Valid @RequestBody CreateTaskRequest request){
        return taskService.createTask(request);
    }

    @GetMapping
    public List<TaskResponse> getAll(
            @RequestParam(required = false) TaskStatus status,
            @RequestParam(required = false) TaskPriority priority,
            @RequestParam(required = false) String q){


        return taskService.getTasks(status, priority, q);
    }

    @GetMapping("/{id}")
    public TaskResponse getById(@PathVariable Long id){
        return taskService.getTask(id);
    }

    @PatchMapping("/{id}")
    public TaskResponse update(@PathVariable Long id, @Valid @RequestBody UpdateTaskRequest request){
        return taskService.updateTask(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        taskService.deleteTask(id);
    }

}

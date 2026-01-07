package md.igor.teamtasks.service;

import md.igor.teamtasks.dto.request.CreateTaskRequest;
import md.igor.teamtasks.dto.request.UpdateTaskRequest;
import md.igor.teamtasks.dto.response.TaskResponse;

import java.util.List;

public interface TaskService {
    TaskResponse createTask(CreateTaskRequest task);
    TaskResponse updateTask(UpdateTaskRequest task);
    List<TaskResponse> getTasks();
    TaskResponse getTask(int id);
    void deleteTask(int id);

}

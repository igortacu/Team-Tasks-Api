package md.igor.teamtasks.service;

import md.igor.teamtasks.dto.request.CreateTaskRequest;
import md.igor.teamtasks.dto.request.UpdateTaskRequest;
import md.igor.teamtasks.dto.response.TaskResponse;
import md.igor.teamtasks.entity.Task;
import md.igor.teamtasks.entity.TaskStatus;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class TaskServiceImpl implements TaskService {
    private final Map<Long, Task> tasks = new HashMap<>();
    private final AtomicLong idSeq = new AtomicLong(1);

    @Override
    public TaskResponse createTask (CreateTaskRequest request) {
        Long id = idSeq.getAndIncrement();
        Instant now = Instant.now();
        Task task = new Task();
        task.getId();
        task.setDescription(request.getDescription());
        task.setPriority(request.getPriority() == null ? null : request.getPriority());
        task.setDeadline(request.getDeadline());
        task.setCreatedAt(now);
        task.setUpdatedAt(now);
        task.setStatus(TaskStatus.Todo);

        tasks.put(id, task);
        return toResponse(task);
    }

    @Override
    public List<TaskResponse> getTasks() {
        List<TaskResponse> responses = new ArrayList<>();
        for (Task task : tasks.values()) {responses.add(toResponse(task))}
        return responses;
    }

    @Override
    public TaskResponse getTask(int id) {
        Task task = tasks.get(id);
        if (task == null) throw new NoSuchElementException("No task with id " + id);
        return toResponse(task);
    }

    @Override
    public TaskResponse update(Long id, UpdateTaskRequest taskRequest){
        Task task = tasks.get(id);
        if (task == null) throw new NoSuchElementException("No task with id " + id);

        if(task.getDescription() != null) task.setDescription(task.getDescription());
        if(task.getPriority() != null) task.setPriority(task.getPriority());
        if(task.getDeadline() != null) task.setDeadline(task.getDeadline());
        if(task.getCreatedAt() != null) task.setCreatedAt(task.getCreatedAt());
        if(task.getUpdatedAt() != null) task.setUpdatedAt(task.getUpdatedAt());
        if(task.getStatus() != null) task.setStatus(task.getStatus());
        tasks.setUpdatedAt(Instant.now());

        return toResponse(tasks);

    }

    @Override
    public void delete(Long id) {
        if (tasks.remove(id) == null) throw new NoSuchElementException("Task not found");
    }

    private TaskResponse toResponse(Task t) {
        return new TaskResponse(
                t.getId(),
                t.getTitle(),
                t.getDescription(),
                t.getStatus(),
                t.getPriority(),
                t.getDeadline(),
                t.getCreatedAt(),
                t.getUpdatedAt()
        );
    }
}

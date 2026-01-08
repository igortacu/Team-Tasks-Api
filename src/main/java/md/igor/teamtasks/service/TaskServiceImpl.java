package md.igor.teamtasks.service;

import md.igor.teamtasks.dto.request.CreateTaskRequest;
import md.igor.teamtasks.dto.request.UpdateTaskRequest;
import md.igor.teamtasks.dto.response.TaskResponse;
import md.igor.teamtasks.entity.Task;
import md.igor.teamtasks.entity.TaskPriority;
import md.igor.teamtasks.entity.TaskStatus;
import md.igor.teamtasks.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private static final AtomicLong idSeq = new AtomicLong(1);

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public TaskResponse createTask (CreateTaskRequest request) {

        Long id = idSeq.getAndIncrement();
        Instant now = Instant.now();
        Task task = new Task();
        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setPriority(request.getPriority());
        task.setDeadline(request.getDeadline());
        task.setCreatedAt(now);
        task.setUpdatedAt(now);
        task.setStatus(request.getStatus());

        Task saved = taskRepository.save(task);
        if(saved == null) throw new RuntimeException("Failed to save task");
        return toResponse(task);
    }

    @Override
    public List<TaskResponse> getTasks(TaskStatus status, TaskPriority priority, String q) {
        List<TaskResponse> responses = new ArrayList<>();
        for (Task task : taskRepository.findAll()) {
            // AND operator: all non-null filters must match
            if(status != null && task.getStatus() != status) continue;
            if(priority != null && task.getPriority() != priority) continue;

            // when q is provided then keep only tasks whose title or description includes q (case-insensitive)
            if(q != null && !q.isBlank()){
                String needle = q.toLowerCase();
                String hay = (task.getTitle() + " " + task.getDescription()).toLowerCase();
                if(!hay.contains(needle)) continue;
            }

            responses.add(toResponse(task));
        }
        return responses;
    }

    @Override
    public TaskResponse getTask(Long id) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Task not found"));
        return toResponse(task);
    }

    @Override
    public TaskResponse updateTask(Long id, UpdateTaskRequest taskRequest){
        Task task = taskRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Task not found"));

        if(taskRequest.getDescription() != null) task.setDescription(taskRequest.getDescription());
        if(taskRequest.getPriority() != null) task.setPriority(taskRequest.getPriority());
        if(taskRequest.getDeadline() != null) task.setDeadline(taskRequest.getDeadline());
        // keep createdAt as is
        task.setUpdatedAt(Instant.now());

        Task saved = taskRepository.save(task);
        return toResponse(task);

    }

    @Override
    public void deleteTask(Long id) {
        if (!taskRepository.existsById(id)) throw new NoSuchElementException("Task not found");
        taskRepository.deleteById(id);    }

    private TaskResponse toResponse(Task t) {
        return new TaskResponse(
                t.getId(),
                t.getTitle(),
                t.getDescription(),
                t.getPriority(),
                t.getStatus(),
                t.getDeadline(),
                t.getCreatedAt(),
                t.getUpdatedAt()
        );
    }
}

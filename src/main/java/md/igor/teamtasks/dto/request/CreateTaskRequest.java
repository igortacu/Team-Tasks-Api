package md.igor.teamtasks.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import md.igor.teamtasks.entity.TaskPriority;
import md.igor.teamtasks.entity.TaskStatus;

import java.time.LocalDate;

public class CreateTaskRequest {

    @NotBlank
    @Size(min = 3, max = 120)
    private String title;

    @Size(max = 1000)
    private String description;

    private TaskPriority priority;
    private TaskStatus status;
    private LocalDate deadline;

    public String getTitle() { return title; }
    public  String getDescription() { return description; }
    public TaskPriority getPriority() { return priority; }
    public  LocalDate getDeadline() { return deadline; }
    public TaskStatus getStatus() { return status; }

    public void setTitle(String title) { this.title = title; }
    public void setDescription(String description) { this.description = description; }
    public void setPriority(TaskPriority priority) { this.priority = priority; }
    public void setDeadline(LocalDate deadline) { this.deadline = deadline; }
    public void setStatus(TaskStatus status) { this.status = status; }



}

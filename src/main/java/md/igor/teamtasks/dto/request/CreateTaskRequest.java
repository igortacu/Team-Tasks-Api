package md.igor.teamtasks.dto.request;

import md.igor.teamtasks.entity.TaskPriority;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class CreateTaskRequest {

    private String title;
    private String description;
    private TaskPriority priority;
    private LocalDate deadline;

    public String getTitle() { return title; }
    public  String getDescription() { return description; }
    public TaskPriority getPriority() { return priority; }
    public  LocalDate getDeadline() { return deadline; }

    public void setTitle(String title) { this.title = title; }
    public void setDescription(String description) { this.description = description; }
    public void setPriority(TaskPriority priority) { this.priority = priority; }
    public void setDeadline(LocalDate deadline) { this.deadline = deadline; }



}

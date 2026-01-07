package md.igor.teamtasks.dto.request;

import md.igor.teamtasks.entity.TaskPriority;

import java.time.LocalDate;

public class UpdateTaskRequest {
    private String id;
    private String description;
    private TaskPriority priority;
    private LocalDate deadline;

    public TaskPriority getPriority() { return priority; }
    public void setPriority(TaskPriority priority) { this.priority = priority; }
    public LocalDate getDeadline() { return deadline; }
    public void setDeadline(LocalDate deadline) { this.deadline = deadline; }
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

}

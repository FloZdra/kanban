package org.flozdra.kanban.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

@Entity
public class ChangeLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date occurred;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private Task task;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    private TaskStatus source;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    private TaskStatus target;

    public ChangeLog() {
    }

    public ChangeLog(Date occurred, Task task, TaskStatus source, TaskStatus target) {
        this.occurred = occurred;
        this.task = task;
        this.source = source;
        this.target = target;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getOccurred() {
        return occurred;
    }

    public void setOccurred(Date occurred) {
        this.occurred = occurred;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public TaskStatus getSource() {
        return source;
    }

    public void setSource(TaskStatus source) {
        this.source = source;
    }

    public TaskStatus getTarget() {
        return target;
    }

    public void setTarget(TaskStatus target) {
        this.target = target;
    }
}

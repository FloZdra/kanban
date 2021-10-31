package org.flozdra.kanban.models;

import javax.persistence.*;
import java.util.Date;

@Entity
public class ChangeLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date occurred;

    @ManyToOne
    private Task task;

    @ManyToOne
    private TaskStatus source;

    @ManyToOne
    private TaskStatus target;

    public ChangeLog() {
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

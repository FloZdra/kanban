package org.flozdra.kanban.models;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private Integer nbHoursForecast;

    private Integer nbHoursReal;

    private Date created;

    @ManyToMany(cascade = CascadeType.DETACH)
    private List<Developer> developers;

    @ManyToOne(cascade = CascadeType.DETACH)
    private TaskType type;

    @ManyToOne(cascade = CascadeType.DETACH)
    private TaskStatus status;

    @OneToMany(mappedBy = "task", cascade = CascadeType.DETACH)
    private List<ChangeLog> changeLogs;

    public Task() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public TaskType getType() {
        return type;
    }

    public void setType(TaskType type) {
        this.type = type;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public Integer getNbHoursForecast() {
        return nbHoursForecast;
    }

    public void setNbHoursForecast(Integer nbHoursForecast) {
        this.nbHoursForecast = nbHoursForecast;
    }

    public Integer getNbHoursReal() {
        return nbHoursReal;
    }

    public void setNbHoursReal(Integer nbHoursReal) {
        this.nbHoursReal = nbHoursReal;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public List<Developer> getDevelopers() {
        return developers;
    }

    public void setDevelopers(List<Developer> developers) {
        this.developers = developers;
    }

    public List<ChangeLog> getChangeLogs() {
        return changeLogs;
    }

    public void setChangeLogs(List<ChangeLog> changeLogs) {
        this.changeLogs = changeLogs;
    }
}

package org.flozdra.kanban.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title is required")
    private String title;

    @NotNull(message = "NbHoursForecast is required")
    private Integer nbHoursForecast;

    @NotNull(message = "NbHoursReal is required")
    private Integer nbHoursReal;

    @Temporal(TemporalType.DATE)
    private Date created;

    @ManyToMany(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    private Set<Developer> developers;

    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    private TaskType type;

    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    private TaskStatus status;

    @OneToMany(mappedBy = "task", cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    private List<ChangeLog> changeLogs;

    public Task() {
    }

    public Task(String title, Integer nbHoursForecast, Integer nbHoursReal, Date created) {
        this.title = title;
        this.nbHoursForecast = nbHoursForecast;
        this.nbHoursReal = nbHoursReal;
        this.created = created;
        this.developers = new HashSet<>();
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

    public Set<Developer> getDevelopers() {
        return developers;
    }

    public void setDevelopers(Set<Developer> developers) {
        this.developers = developers;
    }

    public List<ChangeLog> getChangeLogs() {
        return changeLogs;
    }

    public void setChangeLogs(List<ChangeLog> changeLogs) {
        this.changeLogs = changeLogs;
    }

    public Boolean addDeveloper(Developer developer) {
        return developers.add(developer);
    }

    public Boolean removeDeveloper(Developer developer) {
        return this.developers.remove(developer);
    }
}

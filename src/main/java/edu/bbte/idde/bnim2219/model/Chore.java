package edu.bbte.idde.bnim2219.model;

import java.util.Date;

public class Chore extends BaseEntity {
    private String title;
    private String description;
    private Date deadline;
    private Integer priorityLevel;
    private Boolean done;

    public Chore(Long id, String title, String description, Date deadline, Integer priorityLevel, Boolean done) {
        super(id);
        this.title = title;
        this.description = description;
        this.deadline = deadline;
        this.priorityLevel = priorityLevel;
        this.done = done;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public Integer getPriorityLevel() {
        return priorityLevel;
    }

    public void setPriorityLevel(Integer priorityLevel) {
        this.priorityLevel = priorityLevel;
    }

    public Boolean getDone() {
        return done;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }
}

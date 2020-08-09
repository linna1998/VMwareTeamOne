package com.wmware.teamone.demo.model;

//package com.djamware.react.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "events")
public class Event {
    @Id
    String id;
    String userId;
    String name;
    String link;
    Date startTime;
    Date endTime;
    Category category;
    String description;

    public Event() {
    }

    public Event(String id, String userId, String name, String link, Date startTime, Date endTime, Category category, String description) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.link = link;
        this.startTime = startTime;
        this.endTime = endTime;
        this.category = category;
        this.description = description;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

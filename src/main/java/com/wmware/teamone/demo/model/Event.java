package com.wmware.teamone.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
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

}

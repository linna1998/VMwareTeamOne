package com.wmware.teamone.demo.repository;

import com.wmware.teamone.demo.model.Event;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EventRepository extends MongoRepository<Event, String> {
//    @Override
//    void delete(Event deleted);
}

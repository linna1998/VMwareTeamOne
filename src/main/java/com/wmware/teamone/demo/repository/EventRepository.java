package com.wmware.teamone.demo.repository;

//package com.djamware.react.repositories;

//import com.djamware.react.models.Event;
//import org.springframework.data.repository.CrudRepository;

import com.wmware.teamone.demo.model.Event;
import org.springframework.data.repository.CrudRepository;

public interface EventRepository extends CrudRepository<Event, String> {
    @Override
    void delete(Event deleted);
}
package com.wmware.teamone.demo.controller;

import com.wmware.teamone.demo.model.Event;
import com.wmware.teamone.demo.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class EventController {

    @Autowired
    EventRepository eventRepository;

    @RequestMapping(method = RequestMethod.GET, value = "/events")
    public Iterable<Event> event() {
        return eventRepository.findAll();
    }

    /**
     * Trigger ongoing / future events in start time with increasing order,
     * Filter out the finished events.
     *
     * @return a list of events order by start time in increasing order
     * and have end time >= current time
     */
    @RequestMapping(method = RequestMethod.GET, value = "/events/order_by_time")
    public Iterable<Event> eventInTimeOrder() {
        List<Event> result = new ArrayList<>();
        Iterable<Event> events = eventRepository.findAll();
        Date now = new Date(System.currentTimeMillis());
        // trigger future events
        for (Event event : events) {
            if (event.getStartTime().after(now)) {
                result.add(event);
            }
        }
        // sort events in start time asc order
        result.sort(new Comparator<Event>() {
            @Override
            public int compare(Event event1, Event event2) {
                return event1.getStartTime().compareTo(event2.getStartTime());
            }
        });
        return result;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/events")
    public Event save(@RequestBody Event event) {
        eventRepository.save(event);
        return event;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/events/{id}")
    public Optional<Event> show(@PathVariable String id) {
        return eventRepository.findById(id);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/events/users/{id}")
    public Iterable<Event> showEventsByUser(@PathVariable String id) {
        List<Event> eventsByUser = new ArrayList<>();
        Iterable<Event> allEvents = eventRepository.findAll();
        for (Event event : allEvents) {
            if (event.getUserId().equals(id)) {
                eventsByUser.add(event);
            }
        }
        return eventsByUser;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/events/category/{id}")
    public Iterable<Event> showEventsByCategory(@PathVariable Category id) {
        List<Event> eventsByCategory = new ArrayList<>();
        Iterable<Event> allEvents = eventRepository.findAll();
        for (Event event : allEvents) {
            if (event.getCategory().equals(id)) {
                eventsByCategory.add(event);
            }
        }
        return eventsByCategory;
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/events/{id}")
    public Event update(@PathVariable String id, @RequestBody Event Event) {
        Optional<Event> optEvent = eventRepository.findById(id);
        Event c = optEvent.get();
        if (Event.getUserId() != null) {
            c.setUserId(Event.getUserId());
        }
        if (Event.getName() != null) {
            c.setName(Event.getName());
        }
        if (Event.getLink() != null) {
            c.setLink(Event.getLink());
        }
        if (Event.getStartTime() != null) {
            c.setStartTime(Event.getStartTime());
        }
        if (Event.getEndTime() != null) {
            c.setEndTime(Event.getEndTime());
        }
        if (Event.getCategory() != null) {
            c.setCategory(Event.getCategory());
        }
        if (Event.getDescription() != null) {
            c.setDescription(Event.getDescription());
        }
        eventRepository.save(c);
        return c;
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/events/{id}")
    public String delete(@PathVariable String id) {
        Optional<Event> optEvent = eventRepository.findById(id);
        Event Event = optEvent.get();
        eventRepository.delete(Event);

        return "";
    }

    /**
     * Recommend events:
     * 1. created by this user
     * 2. something random from events repo
     *
     * @param id user id
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/events/recommend/{id}")
    public Iterable<Event> recommend(@PathVariable String id) {
        List<Event> result = new ArrayList<>();
        Iterable<Event> createdByUser = showEventsByUser(id);
        createdByUser.forEach(result::add);
        Iterable<Event> allEvents = eventRepository.findAll();
        for (Event event : allEvents) {
            if (!event.getUserId().equals(id)
                    && Objects.hash(event.getId()) % 3 == 0) {
                // something random to recommend event at this time
                result.add(event);
            }
        }
        return result;
    }
}

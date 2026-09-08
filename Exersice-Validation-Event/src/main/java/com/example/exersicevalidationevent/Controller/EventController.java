package com.example.exersicevalidationevent.Controller;

import com.example.exersicevalidationevent.ApiResponse.ApiResponse;
import com.example.exersicevalidationevent.Event.Event;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("api/v1/event")
public class EventController {
    ArrayList<Event> events = new ArrayList<>();

    @PostMapping("/add")
    public ResponseEntity<?> addEvent(@RequestBody @Valid Event newEvent, Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        events.add(newEvent);
        return ResponseEntity.status(200).body("Event added successfully");
    }

    @GetMapping("/get")
    public ResponseEntity<?> get(){
        return ResponseEntity.status(200).body(events);
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable String id, @RequestBody @Valid Event newEvent, Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        for(int i = 0; i < events.size(); i++){
            if(events.get(i).getId().equals(id)){
                events.set(i, newEvent);
                return ResponseEntity.status(200).body("Event updated");
            }
        }
        return ResponseEntity.status(400).body("Event not found");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable String id){
        for(int i = 0; i < events.size(); i++){
            if(events.get(i).getId().equals(id)){
                events.remove(i);
                return ResponseEntity.status(200).body("Event Deleted Successfully !!");
            }
        }
        return ResponseEntity.status(400).body("Event Not found");
    }

    @PutMapping("/change/capasity/{id}/{capacity}")
    public ResponseEntity<?> changeCapasity(@PathVariable String id, @PathVariable int capacity){
        for(int i = 0; i < events.size(); i++){
            if(events.get(i).getId().equals(id)){
                events.get(i).setCapacity(capacity);
                return ResponseEntity.status(200).body("Capacity has Change");
            }
        }
        return ResponseEntity.status(400).body("Event not found");
    }

    @GetMapping("/get/event/{id}")
    public ResponseEntity<?> getEvents(@PathVariable String id){
        for(int i = 0; i < events.size(); i++){
            if(events.get(i).getId().equals(id)){
                return ResponseEntity.status(200).body(events.get(i));
            }
        }
        return ResponseEntity.status(400).body("Event not found");
    }
}

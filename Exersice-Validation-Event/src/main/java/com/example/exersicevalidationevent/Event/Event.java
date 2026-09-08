package com.example.exersicevalidationevent.Event;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.Month;

@Data
@AllArgsConstructor
public class Event {

    @NotNull
    @Size(min = 3)
    private String id;

    @NotNull
    @Size(min = 16)
    private String description;

    @NotNull
    @Min(26)
    private int capacity;

    @NotNull
    private final LocalDateTime startDate = LocalDateTime.now();

    @NotNull
    private final LocalDateTime endDate = LocalDateTime.of(2026, Month.DECEMBER,29,23,59);

}

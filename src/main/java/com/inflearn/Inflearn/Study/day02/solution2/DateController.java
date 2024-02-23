package com.inflearn.Inflearn.Study.day02.solution2;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.DayOfWeek;
import java.time.LocalDate;

@RestController
public class DateController {

    @GetMapping("/api/v1/day-of-the-week")
    public DateResponse getDate(@RequestParam LocalDate date) {
        DayOfWeek day = date.getDayOfWeek();
        DateResponse response = new DateResponse(day);
        return response;
    }
}

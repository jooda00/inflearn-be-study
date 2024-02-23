package com.inflearn.Inflearn.Study.day02.solution2;

import lombok.Getter;

import java.time.DayOfWeek;

@Getter
public class DateResponse {
    private DayOfWeek dayOfTheWeek;

    public DateResponse(DayOfWeek dayOfTheWeek) {
        this.dayOfTheWeek = dayOfTheWeek;
    }
}

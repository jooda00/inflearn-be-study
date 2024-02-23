package com.inflearn.Inflearn.Study.day04.entity;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class Fruit {
    private Long id;
    private String name;
    private LocalDate warehousingDate;
    private Long price;
    private boolean isSold; // 0 -> false, 1 -> true

    public Fruit(String name, LocalDate warehousingDate, Long price) {
        this.name = name;
        this.warehousingDate = warehousingDate;
        this.price = price;
    }
}

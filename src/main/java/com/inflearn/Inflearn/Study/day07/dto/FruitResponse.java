package com.inflearn.Inflearn.Study.day07.dto;

import com.inflearn.Inflearn.Study.day07.entity.Fruit;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class FruitResponse {
    private String name;
    private Long price;
    private LocalDate warehousingDate;

    public FruitResponse(String name, Long price, LocalDate warehousingDate) {
        this.name = name;
        this.price = price;
        this.warehousingDate = warehousingDate;
    }

    public FruitResponse(Fruit fruit) {
        this.name = fruit.getName();
        this.price = fruit.getPrice();
        this.warehousingDate = fruit.getWarehousingDate();
    }
}

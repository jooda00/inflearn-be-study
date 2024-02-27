package com.inflearn.Inflearn.Study.day07.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor // 기본 생성자
@Getter
public class FruitRequest {
    private String name;
    private LocalDate warehousingDate;
    private Long price;

    public FruitRequest(String name, LocalDate warehousingDate, Long price) {
        this.name = name;
        this.warehousingDate = warehousingDate;
        this.price = price;
    }
}

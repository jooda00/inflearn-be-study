package com.inflearn.Inflearn.Study.day04.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FruitSoldResponse {
    private Long salesAmount;
    private Long noSalesAmount;

    public FruitSoldResponse(Long salesAmount, Long noSalesAmount) {
        this.salesAmount = salesAmount;
        this.noSalesAmount = noSalesAmount;
    }
}

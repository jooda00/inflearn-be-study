package com.inflearn.Inflearn.Study.day07.dto;

import lombok.Getter;

@Getter
public class FruitCountResponse {
    private Long count;

    public FruitCountResponse(Long count) {
        this.count = count;
    }
}

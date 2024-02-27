package com.inflearn.Inflearn.Study.day07.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FruitUpdateRequest {
    private Long id;

    public FruitUpdateRequest(Long id) {
        this.id = id;
    }
}

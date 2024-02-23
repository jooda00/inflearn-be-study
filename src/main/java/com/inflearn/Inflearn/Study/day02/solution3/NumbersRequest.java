package com.inflearn.Inflearn.Study.day02.solution3;

import lombok.Getter;

import java.util.List;

@Getter
public class NumbersRequest {
    private List<Integer> numbers;

    public NumbersRequest(List<Integer> numbers) {
        this.numbers = numbers;
    }

    /* 기본 생성자 필요 */
    public NumbersRequest() {
    }
}

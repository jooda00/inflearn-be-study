package com.inflearn.Inflearn.Study.day02.solution1;

import lombok.Getter;

@Getter
public class CalculateResponse {
    private int add;
    private int minus;
    private int multiply;

    public CalculateResponse(int add, int minus, int multiply) {
        this.add = add;
        this.minus = minus;
        this.multiply = multiply;
    }
}

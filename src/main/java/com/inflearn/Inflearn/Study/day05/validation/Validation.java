package com.inflearn.Inflearn.Study.day05.validation;

// 예외 처리 부분
public class Validation {
    public static final void validateInputNumberIsOverZero(int count) {
        if(count < 1) {
            throw new IllegalArgumentException("입력하는 숫자는 1이상이어야 합니다.");
        }
    }
}

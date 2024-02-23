package com.inflearn.Inflearn.Study.day01;

import org.springframework.stereotype.Component;

@Component
public class Week01Main {
    @CustomAnnotation
    public void doSomething() {
        int a = 10;
        int b = 20;
        int c = a * b;
        System.out.println("result : " + c);
    }
}

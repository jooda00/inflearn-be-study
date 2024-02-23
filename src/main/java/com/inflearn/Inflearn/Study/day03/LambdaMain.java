package com.inflearn.Inflearn.Study.day03;

public class LambdaMain {
    public static void main(String[] args) {
        // 첫 번째 코드 실행 시간 측정
        long startTime1 = System.currentTimeMillis();

        // 익명 클래스
        Runnable runnable1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello, World!");
            }
        };
        // 스레드 생성
        Thread thread1 = new Thread(runnable1);
        // 스레드 실행
        thread1.start();

        long endTime1 = System.currentTimeMillis();
        long executionTime1 = endTime1 - startTime1;
        System.out.println("첫 번째 코드 실행 시간: " + executionTime1 + "밀리초");

        // 람다식
        Runnable runnable2 = () -> {
            System.out.println("Hello, World!");
        };

        Thread thread2 = new Thread(runnable2);
        thread2.start();
    }
}

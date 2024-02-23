package com.inflearn.Inflearn.Study.day05.view;

public class OutputView {
    public static void printOutput(int[] dice) {
        int len = dice.length;
        for(int i = 1; i <= len - 1; i++) {
            System.out.println(i + "은 " + dice[i] + "번 나왔습니다.");
        }
    }
}

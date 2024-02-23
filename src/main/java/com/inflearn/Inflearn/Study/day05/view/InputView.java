package com.inflearn.Inflearn.Study.day05.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InputView {
    public static int readInput() throws IOException {
        System.out.print("반복 횟수를 입력해 주세요 : ");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int count = Integer.parseInt(br.readLine());
        return count;
    }
}

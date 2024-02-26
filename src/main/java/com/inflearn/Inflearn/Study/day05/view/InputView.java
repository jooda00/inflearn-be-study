package com.inflearn.Inflearn.Study.day05.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 입력 화면 부부
public class InputView {
    public static int readInput() throws IOException {
        System.out.print("숫자를 입력하세요 : ");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int count = Integer.parseInt(br.readLine());
        return count;
    }
}

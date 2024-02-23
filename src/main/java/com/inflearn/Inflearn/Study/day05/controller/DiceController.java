package com.inflearn.Inflearn.Study.day05.controller;

import com.inflearn.Inflearn.Study.day05.view.InputView;
import com.inflearn.Inflearn.Study.day05.view.OutputView;

import java.io.IOException;

public class DiceController {
    static int[] dice = new int[7];
    public void play() throws IOException {
        int count = InputView.readInput();
        for(int i = 0; i < count; i++) {
            int num = extractNumber();
            dice[num]++;
        }
        OutputView.printOutput(dice);
    }

    public int extractNumber() {
        int num = (int) (Math.random() * 6) + 1;
        return num;
    }
}

package com.inflearn.Inflearn.Study.day05.controller;

import com.inflearn.Inflearn.Study.day05.validation.Validation;
import com.inflearn.Inflearn.Study.day05.view.InputView;
import com.inflearn.Inflearn.Study.day05.view.OutputView;

import java.io.IOException;

// 컨트롤러 부분
public class DiceController {
    private static final int SIZE_OF_DICE = 6;
    int[] dice = new int[SIZE_OF_DICE + 1];

    public void play() throws IOException {
        try {
            int count = InputView.readInput();
            Validation.validateInputNumberIsOverZero(count);
            roleDice(count);
            OutputView.printOutput(dice);
        }
        catch (Exception e) {
            System.out.println(e);
            play();
        }
    }

    private int extractNumber() {
        int num = (int) (Math.random() * SIZE_OF_DICE) + 1;
        return num;
    }

    private void roleDice(int count) {
        for(int i = 0; i < count; i++) {
            int num = extractNumber();
            dice[num]++;
        }
    }
}

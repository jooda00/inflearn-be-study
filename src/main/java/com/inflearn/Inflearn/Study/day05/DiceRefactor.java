package com.inflearn.Inflearn.Study.day05;

import com.inflearn.Inflearn.Study.day05.controller.DiceController;

import java.io.IOException;

// 메인 부분
public class DiceRefactor {
    public static void main(String[] args) {
        DiceController diceController = new DiceController();
        diceController.play();
    }
}

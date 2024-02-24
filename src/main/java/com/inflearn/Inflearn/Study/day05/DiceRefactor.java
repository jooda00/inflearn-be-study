package com.inflearn.Inflearn.Study.day05;

import com.inflearn.Inflearn.Study.day05.controller.DiceController;

import java.io.IOException;

public class DiceRefactor {
    public static void main(String[] args) throws IOException {
        DiceController diceController = new DiceController();
        diceController.play();
    }
}

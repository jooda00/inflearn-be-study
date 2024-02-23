package com.inflearn.Inflearn.Study.day02.solution1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalculateController {

    @GetMapping("/api/v1/calc")
    public CalculateResponse calculate(@RequestParam int num1, @RequestParam int num2) {
        int addResult = num1 + num2;
        int minusResult = num1 - num2;
        int multiplyResult = num1 * num2;
        CalculateResponse response = new CalculateResponse(addResult, minusResult, multiplyResult);
        return response;
    }

}

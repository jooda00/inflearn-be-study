package com.inflearn.Inflearn.Study.day02.solution3;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NumbersController {

    @PostMapping("/api/v1/numbers")
    public Integer addNumbers(@RequestBody NumbersRequest request) {
        int sum = 0;
        for(Integer num : request.getNumbers()) {
            sum += num;
        }
        return sum;
    }
}

package com.inflearn.Inflearn.Study.day06.controller;

import com.inflearn.Inflearn.Study.day06.dto.FruitRequest;
import com.inflearn.Inflearn.Study.day06.dto.FruitSoldResponse;
import com.inflearn.Inflearn.Study.day06.dto.FruitUpdateRequest;
import com.inflearn.Inflearn.Study.day06.service.FruitService;
import org.springframework.web.bind.annotation.*;

@RestController
public class FruitController {

    private final FruitService fruitService;

    public FruitController(FruitService fruitService) {
        this.fruitService = fruitService;
    }

    @PostMapping("/api/v2/fruit")
    public void saveFruit(@RequestBody FruitRequest request) {
        fruitService.saveFruit(request);
    }

    @PutMapping("/api/v2/fruit")
    public void sellFruit(@RequestBody FruitUpdateRequest updateRequest) {
        fruitService.sellFruit(updateRequest);
    }

    @GetMapping("/api/v2/fruit/stat")
    public FruitSoldResponse getFruitIsSoldOrNot(@RequestParam String name) {
        return fruitService.getFruitIsSoldOrNot(name);
    }
}
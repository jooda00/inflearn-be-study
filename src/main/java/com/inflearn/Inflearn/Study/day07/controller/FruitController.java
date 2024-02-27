package com.inflearn.Inflearn.Study.day07.controller;

import com.inflearn.Inflearn.Study.day07.dto.FruitCountResponse;
import com.inflearn.Inflearn.Study.day07.dto.FruitRequest;
import com.inflearn.Inflearn.Study.day07.dto.FruitSoldResponse;
import com.inflearn.Inflearn.Study.day07.dto.FruitUpdateRequest;
import com.inflearn.Inflearn.Study.day07.service.FruitService;
import org.springframework.web.bind.annotation.*;

@RestController
public class FruitController {

    private final FruitService fruitService;

    public FruitController(FruitService fruitService) {
        this.fruitService = fruitService;
    }

    @PostMapping("/api/v3/fruit")
    public void saveFruit(@RequestBody FruitRequest request) {
        fruitService.saveFruit(request);
    }

    @PutMapping("/api/v3/fruit")
    public void sellFruit(@RequestBody FruitUpdateRequest updateRequest) {
        fruitService.sellFruit(updateRequest);
    }

    @GetMapping("/api/v3/fruit/stat")
    public FruitSoldResponse getFruitIsSoldOrNot(@RequestParam String name) {
        return fruitService.getFruitIsSoldOrNot(name);
    }

    @GetMapping("/api/v3/fruit/count")
    public FruitCountResponse getFruitCountByName(@RequestParam String name) {
        return fruitService.getFruitCountByName(name);
    }
}
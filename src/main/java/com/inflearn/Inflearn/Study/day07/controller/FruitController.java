package com.inflearn.Inflearn.Study.day07.controller;

import com.inflearn.Inflearn.Study.day07.dto.*;
import com.inflearn.Inflearn.Study.day07.entity.FruitPriceOption;
import com.inflearn.Inflearn.Study.day07.service.FruitService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        return fruitService.getFruitIsSoldOrNotV1(name);
    }

    @GetMapping("/api/v3/v2/fruit/stat")
    public FruitSoldResponse getFruitIsSoldOrNotV2(@RequestParam String name) {
        return fruitService.getFruitIsSoldOrNotV2(name);
    }

    @GetMapping("/api/v3/fruit/count")
    public FruitCountResponse getFruitCountByName(@RequestParam String name) {
        return fruitService.getFruitCountByName(name);
    }

    @GetMapping("/api/v3/fruit/list")
    public List<FruitResponse> getFruitByPrice(@RequestParam String opt, @RequestParam Long price) {
        if(opt.equals("GTE")) {
            return fruitService.getFruitGreaterThanEqualByPrice(price);
        }
        return fruitService.getFruitLessThanEqualByPrice(price);
    }

    @GetMapping("/api/v3/fruit/dsl/list")
    public List<FruitResponse> getFruitByPriceUsingQuerydsl(@RequestParam FruitPriceOption opt, @RequestParam Long price) {
        return fruitService.getFruitByPriceIsSoldFalse(opt, price);
    }
}
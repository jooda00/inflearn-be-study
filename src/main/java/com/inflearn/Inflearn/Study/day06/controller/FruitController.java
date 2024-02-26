package com.inflearn.Inflearn.Study.day06.controller;

import com.inflearn.Inflearn.Study.day06.dto.FruitRequest;
import com.inflearn.Inflearn.Study.day06.dto.FruitSoldResponse;
import com.inflearn.Inflearn.Study.day06.dto.FruitUpdateRequest;
import com.inflearn.Inflearn.Study.day06.entity.Fruit;
import com.inflearn.Inflearn.Study.day06.service.FruitService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class FruitController {

    private final FruitService fruitService;
    private final JdbcTemplate jdbcTemplate;

    public FruitController(FruitService fruitService, JdbcTemplate jdbcTemplate) {
        this.fruitService = fruitService;
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostMapping("/api/v2/fruit")
    public void saveFruit(@RequestBody FruitRequest request) {
        fruitService.saveFruit(request, jdbcTemplate);
    }

    @PutMapping("/api/v2/fruit")
    public void sellFruit(@RequestBody FruitUpdateRequest updateRequest) {
        fruitService.sellFruit(updateRequest, jdbcTemplate);
    }

    @GetMapping("/api/v2/fruit/stat")
    public FruitSoldResponse getFruitIsSoldOrNot(@RequestParam String name) {
        return fruitService.getFruitIsSoldOrNot(name, jdbcTemplate);
    }
}
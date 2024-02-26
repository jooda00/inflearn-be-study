package com.inflearn.Inflearn.Study.day04.controller;

import com.inflearn.Inflearn.Study.day04.dto.FruitRequest;
import com.inflearn.Inflearn.Study.day04.dto.FruitSoldResponse;
import com.inflearn.Inflearn.Study.day04.dto.FruitUpdateRequest;
import com.inflearn.Inflearn.Study.day04.entity.Fruit;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class FruitControllerBeforeLayered {

    private final JdbcTemplate jdbcTemplate;

    public FruitControllerBeforeLayered(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostMapping("/api/v1/fruit")
    public void saveFruit(@RequestBody FruitRequest request) {
        String sql = "insert into fruit(name, warehousingDate, price) values (?, ?, ?)";
        Fruit fruit = new Fruit(request.getName(), request.getWarehousingDate(), request.getPrice());
        jdbcTemplate.update(sql, fruit.getName(), fruit.getWarehousingDate(), fruit.getPrice());
    }

    @PutMapping("/api/v1/fruit")
    public void sellFruit(@RequestBody FruitUpdateRequest updateRequest) {
        String readSql = "select * from fruit where id = ?";
        boolean isExistFruit = jdbcTemplate.query(readSql, (rs, rowNum) -> 0, updateRequest.getId()).isEmpty();
        if(isExistFruit) {
            throw new IllegalArgumentException("데이터베이스에 팔 과일이 없습니다.");
        }
        String sql = "update fruit set is_sold = 1 where id = ?";

        jdbcTemplate.update(sql, updateRequest.getId());
    }

    /*@GetMapping("/api/v1/fruit/stat")
    public FruitSoldResponse getFruitIsSoldOrNot(@RequestParam String name) {
        Long soldPrice = jdbcTemplate.queryForObject(
                "select sum(price) from fruit where name = ? and is_sold = 1", Long.class, name
        );
        Long notSoldPrice = jdbcTemplate.queryForObject(
                "select sum(price) from fruit where name = ? and is_sold = 0", Long.class, name
        );
        FruitSoldResponse response = new FruitSoldResponse(soldPrice, notSoldPrice);
        return response;
    }*/

    @GetMapping("/api/v1/fruit/stat")
    public FruitSoldResponse getFruitIsSoldOrNot(@RequestParam String name) {
        String sql = "select is_sold, sum(price) from fruit where name = ? group by is_sold";
        boolean isExistFruit = jdbcTemplate.query(sql, (rs, rowNum) -> 0, name).isEmpty();
        if(isExistFruit) {
            throw new IllegalArgumentException("이름과 일치하는 과일이 없습니다.");
        }
        Map<Boolean, Long> soldStatusMap = jdbcTemplate.query(sql, new Object[]{name}, rs -> {
            Map<Boolean, Long> map = new HashMap<>();
            while(rs.next()) {
                map.put(rs.getBoolean(1), rs.getLong(2));
            }
            return map;
        });

        Long soldPrice = soldStatusMap.getOrDefault(true, 0L);
        Long notSoldPrice = soldStatusMap.getOrDefault(false, 0L);
        return new FruitSoldResponse(soldPrice, notSoldPrice);
    }
}
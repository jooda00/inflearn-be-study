package com.inflearn.Inflearn.Study.day06.service;

import com.inflearn.Inflearn.Study.day06.dto.FruitRequest;
import com.inflearn.Inflearn.Study.day06.dto.FruitSoldResponse;
import com.inflearn.Inflearn.Study.day06.dto.FruitUpdateRequest;
import com.inflearn.Inflearn.Study.day06.entity.Fruit;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service // 스프링 빈으로 만들어 줌
public class FruitService {

    public void saveFruit(FruitRequest request, JdbcTemplate jdbcTemplate) {
        String sql = "insert into fruit(name, warehousingDate, price) values (?, ?, ?)";
        Fruit fruit = new Fruit(request.getName(), request.getWarehousingDate(), request.getPrice());
        jdbcTemplate.update(sql, fruit.getName(), fruit.getWarehousingDate(), fruit.getPrice());
    }

    public void sellFruit(FruitUpdateRequest updateRequest, JdbcTemplate jdbcTemplate) {
        String readSql = "select * from fruit where id = ?";
        boolean isExistFruit = jdbcTemplate.query(readSql, (rs, rowNum) -> 0, updateRequest.getId()).isEmpty();
        if(isExistFruit) {
            throw new IllegalArgumentException("데이터베이스에 팔 과일이 없습니다.");
        }
        String sql = "update fruit set is_sold = 1 where id = ?";

        jdbcTemplate.update(sql, updateRequest.getId());
    }

    public FruitSoldResponse getFruitIsSoldOrNot(String name, JdbcTemplate jdbcTemplate) {
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

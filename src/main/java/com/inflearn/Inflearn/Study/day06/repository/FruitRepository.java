package com.inflearn.Inflearn.Study.day06.repository;

import com.inflearn.Inflearn.Study.day06.dto.FruitRequest;
import com.inflearn.Inflearn.Study.day06.dto.FruitSoldResponse;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Repository
public class FruitRepository {
    private final JdbcTemplate jdbcTemplate;

    public FruitRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(String name, LocalDate warehousingDate, Long price) {
        String sql = "insert into fruit(name, warehousingDate, price) values (?, ?, ?)";
        jdbcTemplate.update(sql, name, warehousingDate, price);
    }

    public void update(Long id) {
        String readSql = "select * from fruit where id = ?";
        boolean isExistFruit = jdbcTemplate.query(readSql, (rs, rowNum) -> 0, id).isEmpty();
        if(isExistFruit) {
            throw new IllegalArgumentException("데이터베이스에 팔 과일이 없습니다.");
        }
        String sql = "update fruit set is_sold = 1 where id = ?";
        jdbcTemplate.update(sql, id);
    }

    public FruitSoldResponse getList(String name) {
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

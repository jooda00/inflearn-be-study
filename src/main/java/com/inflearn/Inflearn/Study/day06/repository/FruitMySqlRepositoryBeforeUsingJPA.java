package com.inflearn.Inflearn.Study.day06.repository;

import com.inflearn.Inflearn.Study.day06.dto.FruitSoldResponse;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Primary
@Repository // 인터페이스 구현체에서 붙여주면 된다.
public class FruitMySqlRepositoryBeforeUsingJPA implements FruitRepositoryBeforeUsingJPA {
    private final JdbcTemplate jdbcTemplate;

    public FruitMySqlRepositoryBeforeUsingJPA(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(String name, LocalDate warehousingDate, Long price) {
        String sql = "insert into fruit(name, warehousingDate, price) values (?, ?, ?)";
        jdbcTemplate.update(sql, name, warehousingDate, price);
    }

    public void update(Long id) {
        String sql = "update fruit set is_sold = 1 where id = ?";
        jdbcTemplate.update(sql, id);
    }

    public FruitSoldResponse getList(String name) {
        String sql = "select is_sold, sum(price) from fruit where name = ? group by is_sold";
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

    public boolean isFruitExistById(long id) {
        String sql = "select * from fruit where id = ?";
        return jdbcTemplate.query(sql, (rs, rowNum) -> 0, id).isEmpty();
    }

    public boolean isFruitExistByName(String name) {
        String sql = "select * from fruit where name = ?";
        return jdbcTemplate.query(sql, (rs, rowNum) -> 0, name).isEmpty();
    }
}

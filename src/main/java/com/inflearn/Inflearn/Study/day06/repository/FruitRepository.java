package com.inflearn.Inflearn.Study.day06.repository;

import com.inflearn.Inflearn.Study.day06.dto.FruitSoldResponse;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface FruitRepository {
    void save(String name, LocalDate warehousingDate, Long price);
    void update(Long id);
    FruitSoldResponse getList(String name);
    boolean isFruitExistById(long id);
    boolean isFruitExistByName(String name);
}

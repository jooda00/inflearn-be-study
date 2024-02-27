package com.inflearn.Inflearn.Study.day07.repository;

import com.inflearn.Inflearn.Study.day07.entity.Fruit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FruitRepository extends JpaRepository<Fruit, Long> {
    List<Fruit> findByName(String name);

    @Query(value = "select f.is_sold, sum(f.price) from fruit f where f.name = :name group by f.is_sold", nativeQuery = true)
    List<Object[]> findIsSoldAndPriceByName(@Param("name") String name);
}

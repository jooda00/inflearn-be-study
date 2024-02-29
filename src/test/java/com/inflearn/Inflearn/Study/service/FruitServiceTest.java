package com.inflearn.Inflearn.Study.service;

import com.inflearn.Inflearn.Study.day07.entity.Fruit;
import com.inflearn.Inflearn.Study.day07.repository.FruitRepository;
import com.inflearn.Inflearn.Study.day07.service.FruitService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource("classpath:application-test.yml") //test용 yml 파일 설정
public class FruitServiceTest {

    private final FruitRepository fruitRepository;
    private final FruitService fruitService;

    @Autowired(required = false)
    public FruitServiceTest(FruitRepository fruitRepository, FruitService fruitService) {
        this.fruitRepository = fruitRepository;
        this.fruitService = fruitService;
    }

    @BeforeEach
    public void setUp() {
        List<Fruit> fruits = new ArrayList<>();
        for (int i = 0; i < 5000; i++) {
            Fruit fruit = new Fruit("사과", LocalDate.now(), 2000L);
            fruits.add(fruit);
        }
        fruitRepository.saveAll(fruits);
    }

    @Test
    public void print() {
        System.out.println("test");
    }

    @Test
    public void JPAWithQueryDSL() {
        long startTime = System.currentTimeMillis();
        fruitService.getFruitIsSoldOrNotV1("사과");
        long endTime = System.currentTimeMillis();
        System.out.println("v1 : " + (endTime - startTime));
    }

    @Test
    public void OnlyQueryDSL() {
        long startTime = System.currentTimeMillis();
        fruitService.getFruitIsSoldOrNotV2("사과");
        long endTime = System.currentTimeMillis();
        System.out.println("v2 : " + (endTime - startTime));
    }
}

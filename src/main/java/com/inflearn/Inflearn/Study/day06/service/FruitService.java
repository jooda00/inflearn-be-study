package com.inflearn.Inflearn.Study.day06.service;

import com.inflearn.Inflearn.Study.day06.dto.FruitRequest;
import com.inflearn.Inflearn.Study.day06.dto.FruitSoldResponse;
import com.inflearn.Inflearn.Study.day06.dto.FruitUpdateRequest;
import com.inflearn.Inflearn.Study.day06.entity.Fruit;
import com.inflearn.Inflearn.Study.day06.repository.FruitRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service // 스프링 빈으로 만들어 줌
public class FruitService {

    private final FruitRepository fruitRepository;

    public FruitService(FruitRepository fruitRepository) {
        this.fruitRepository = fruitRepository;
    }

    public void saveFruit(FruitRequest request) {
        Fruit fruit = new Fruit(request.getName(), request.getWarehousingDate(), request.getPrice());
        fruitRepository.save(fruit.getName(), fruit.getWarehousingDate(), fruit.getPrice());
    }

    public void sellFruit(FruitUpdateRequest updateRequest) {
        fruitRepository.update(updateRequest.getId());
    }

    public FruitSoldResponse getFruitIsSoldOrNot(String name) {
        return fruitRepository.getList(name);
    }
}

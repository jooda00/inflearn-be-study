package com.inflearn.Inflearn.Study.day06.service;

import com.inflearn.Inflearn.Study.day06.dto.FruitRequest;
import com.inflearn.Inflearn.Study.day06.dto.FruitSoldResponse;
import com.inflearn.Inflearn.Study.day06.dto.FruitUpdateRequest;
import com.inflearn.Inflearn.Study.day06.entity.Fruit;
import com.inflearn.Inflearn.Study.day06.repository.FruitRepositoryBeforeUsingJPA;
import org.springframework.stereotype.Service;

@Service // 스프링 빈으로 만들어 줌
public class FruitServiceBeforeUsingJPA {

    private final FruitRepositoryBeforeUsingJPA fruitRepository;

    public FruitServiceBeforeUsingJPA(FruitRepositoryBeforeUsingJPA fruitRepository) {
        this.fruitRepository = fruitRepository;
    }

    public void saveFruit(FruitRequest request) {
        Fruit fruit = new Fruit(request.getName(), request.getWarehousingDate(), request.getPrice());
        fruitRepository.save(fruit.getName(), fruit.getWarehousingDate(), fruit.getPrice());
    }

    public void sellFruit(FruitUpdateRequest updateRequest) {
        boolean isExist = fruitRepository.isFruitExistById(updateRequest.getId());
        if (isExist) {
            throw new IllegalArgumentException("데이터베이스에 팔 과일이 없습니다.");
        }
        fruitRepository.update(updateRequest.getId());
    }

    public FruitSoldResponse getFruitIsSoldOrNot(String name) {
        boolean isExist = fruitRepository.isFruitExistByName(name);
        if (isExist) {
            throw new IllegalArgumentException("이름과 일치하는 과일이 없습니다.");
        }
        return fruitRepository.getList(name);
    }
}

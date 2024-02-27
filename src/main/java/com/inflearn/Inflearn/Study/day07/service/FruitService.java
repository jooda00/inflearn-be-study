package com.inflearn.Inflearn.Study.day07.service;

import com.inflearn.Inflearn.Study.day07.dto.FruitRequest;
import com.inflearn.Inflearn.Study.day07.dto.FruitSoldResponse;
import com.inflearn.Inflearn.Study.day07.dto.FruitUpdateRequest;
import com.inflearn.Inflearn.Study.day07.entity.Fruit;
import com.inflearn.Inflearn.Study.day07.repository.FruitRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service // 스프링 빈으로 만들어 줌
@Transactional(readOnly = true) // default값이 false
public class FruitService {

    private final FruitRepository fruitRepository;

    public FruitService(FruitRepository fruitRepository) {
        this.fruitRepository = fruitRepository;
    }

    @Transactional
    public void saveFruit(FruitRequest request) {
        Fruit fruit = new Fruit(request.getName(), request.getWarehousingDate(), request.getPrice());
        fruitRepository.save(fruit);
    }

    @Transactional
    public void sellFruit(FruitUpdateRequest updateRequest) {
        Optional<Fruit> fruit = Optional.ofNullable(fruitRepository.findById(updateRequest.getId())
                .orElseThrow(() -> new IllegalArgumentException("데이터베이스에 과일이 없습니다.")));
        fruit.get().soldFruit();
    }

    public FruitSoldResponse getFruitIsSoldOrNot(String name) {
        List<Fruit> fruit = fruitRepository.findByName(name);
        if(fruit.size() == 0) {
            throw new IllegalArgumentException("이름과 일치하는 과일이 없습니다.");
        }
        List<Object[]> fruits = fruitRepository.findIsSoldAndPriceByName(name);
        Long isSold = 0L;
        Long isNotSold = 0L;
        for(Object[] res : fruits) {
            if((Boolean) res[0]) {
                isSold = ((BigDecimal) res[1]).longValue();
            }
            else {
                isNotSold = ((BigDecimal) res[1]).longValue();
            }
        }
        return new FruitSoldResponse(isSold, isNotSold);
    }
}

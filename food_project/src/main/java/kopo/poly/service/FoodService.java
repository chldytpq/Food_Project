package kopo.poly.service;

import kopo.poly.dto.FoodDto;
import kopo.poly.entity.Food;
import kopo.poly.repository.FoodRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class FoodService {

    private final FoodRepository foodRepository;

    @Transactional
    public List<FoodDto> getfoodList(){

        log.info(this.getClass().getName() + "getfoodList Start!");

        List<Food> foods = foodRepository.findAll();
        List<FoodDto> fooddtoList = new ArrayList<>();

        for(Food food : foods){
            FoodDto fooddto = FoodDto.builder()
                    .id(food.getId())
                    .kcal(food.getKcal())
                    .na(food.getNa())
                    .tan(food.getTan())
                    .pro(food.getPro())
                    .fat(food.getFat())
                    .build();

            fooddtoList.add(fooddto);
        }

        log.info(this.getClass().getName() + "getfoodList Start!");

        return fooddtoList;
    }

    public Food saveFood(Food food){

        log.info(this.getClass().getName() + "saveFood Start!");

        log.info(this.getClass().getName() + "saveFood Stop!");

        return foodRepository.save(food);
    }

    @Transactional
    public FoodDto getfoodDetail(Long getid){

        log.info(this.getClass().getName() + "getfoodDetail Start!");

        Optional<Food> food = foodRepository.findById(getid);

        FoodDto fooddto = FoodDto.builder()
                .id(food.get().getId())
                .kcal(food.get().getKcal())
                .na(food.get().getNa())
                .tan(food.get().getTan())
                .pro(food.get().getPro())
                .fat(food.get().getFat())
                .build();

        log.info(this.getClass().getName() + "getfoodDetail Start!");

        return fooddto;
    }

    public void deleteFood(Long getid){

        log.info(this.getClass().getName() + "deletFood Start!");

        foodRepository.deleteById(getid);

        log.info(this.getClass().getName() + "deleteFood Stop!");

    }

}

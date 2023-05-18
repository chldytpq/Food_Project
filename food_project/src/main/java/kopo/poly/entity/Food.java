package kopo.poly.entity;

import kopo.poly.dto.FoodDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "food")
@Getter
@NoArgsConstructor
@DynamicUpdate
@Builder

public class Food {

    @Id
    @Column(name = "food_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String memberEmail;  //회원이메일

    @NotNull
    private int kcal;

    @NotNull
    private int na;

    @NotNull
    private int tan;

    @NotNull
    private int pro;

    @NotNull
    private int fat;

    Food(Long id, @NotNull String memberEmail, @NotNull int kcal, @NotNull int na, @NotNull int tan, @NotNull int pro, @NotNull int fat){
        this.id = id;
        this.memberEmail = memberEmail;
        this.kcal = kcal;
        this.na = na;
        this.tan = tan;
        this.pro = pro;
        this.fat = fat;
    }

    public static Food toEntity(FoodDto fooddto){

        Long id = fooddto.getId();
        String memberEmail = fooddto.getMemberEmail();
        int kcal = fooddto.getKcal();
        int na = fooddto.getNa();
        int tan = fooddto.getTan();
        int pro = fooddto.getPro();
        int fat = fooddto.getFat();
        Food food = Food.builder().
                id(id).memberEmail(memberEmail).kcal(kcal).na(na).tan(tan).pro(pro).fat(fat)
                .build();

        return food;
    }
}

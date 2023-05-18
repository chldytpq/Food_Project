package kopo.poly.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@NoArgsConstructor
public class FoodDto {

    private Long id;

    private String memberEmail;  //회원이메일

    private int kcal;

    private int na;

    private int tan;

    private int pro;

    private int fat;

    FoodDto(Long id, @NotNull String memberEmail, @NotNull int kcal, @NotNull int na, @NotNull int tan, @NotNull int pro, @NotNull int fat){
        this.id = id;
        this.memberEmail = memberEmail;
        this.kcal = kcal;
        this.na = na;
        this.tan = tan;
        this.pro = pro;
        this.fat = fat;
    }

}

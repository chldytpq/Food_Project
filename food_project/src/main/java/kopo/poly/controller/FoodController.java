package kopo.poly.controller;

import kopo.poly.config.CmmUtil;
import kopo.poly.dto.FoodDto;
import kopo.poly.entity.Food;
import kopo.poly.service.FoodService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.awt.event.WindowFocusListener;
import java.util.List;

@Controller
@RequestMapping("/food")
@RequiredArgsConstructor
@Slf4j

public class FoodController {

    private final FoodService foodService;

    @GetMapping(value = "/main")
    public String foodlist(Model model){

        log.info(this.getClass().getName() + "foodList Start!");

        List<FoodDto> fooddtoList = foodService.getfoodList();
        model.addAttribute("foodList", fooddtoList);

        log.info(this.getClass().getName() + "foodList Stop!");

        return "main";
    }

    @GetMapping(value = "/insert")
    public String foodinsert(Model model){
        model.addAttribute("fooddto", new FoodDto());
        return "/food/foodInset";
    }


    @PostMapping(value = "/insert")
    public String foodinset(HttpServletRequest request, Model model, HttpSession session) {

        log.info(this.getClass().getName() + "foodinset Start!");

        try {

            String memeberEmail = CmmUtil.nvl((String) session.getAttribute("SS_MEMBER_EMAIL"));
            int kcal = Integer.parseInt(request.getParameter("kacl"));
            int na = Integer.parseInt(request.getParameter("na"));
            int tan = Integer.parseInt(request.getParameter("tan"));
            int pro = Integer.parseInt(request.getParameter("pro"));
            int fat = Integer.parseInt(request.getParameter("fat"));

            log.info("memberEmail" + memeberEmail);
            log.info("kcal" + kcal);
            log.info("na" + na);
            log.info("tan" + tan);
            log.info("pro" + pro);
            log.info("fat" + fat);

            FoodDto fooddto = new FoodDto();
            fooddto.setMemberEmail(memeberEmail);
            fooddto.setKcal(kcal);
            fooddto.setNa(na);
            fooddto.setTan(tan);
            fooddto.setPro(pro);
            fooddto.setFat(fat);

            Food food = Food.toEntity(fooddto);
            foodService.saveFood(food);

        } catch (Exception e){
            String msg = "저장을 실패하였습니다" + e.getMessage();
            model.addAttribute("errorMessage", msg);
            return "/food/foodInsert";
        }

        log.info(this.getClass().getName() + "foodinset Stop!");

        return "main";
    }

    @GetMapping(value = "/{foodid}")
    public String fooddetail(HttpServletRequest request, Model model){

        log.info(this.getClass().getName() + "foodDetail Start!");

        Long getid = Long.parseLong(request.getParameter("id"));
        log.info("id" + getid);

        FoodDto fooddto = foodService.getfoodDetail(getid);

        model.addAttribute("fooddtodetail" ,fooddto);

        log.info(this.getClass().getName() + "foodDetail Stop!");

        return "/food/foodDetail";
    }

    @PostMapping(value = "/update/{foodid}")
    public String foodupdate(HttpServletRequest request, Model model) {

        log.info(this.getClass().getName() + "foodUpdate Start!");

        try {

            Long getid = Long.parseLong(request.getParameter("id"));
            int kcal = Integer.parseInt(request.getParameter("kacl"));
            int na = Integer.parseInt(request.getParameter("na"));
            int tan = Integer.parseInt(request.getParameter("tan"));
            int pro = Integer.parseInt(request.getParameter("pro"));
            int fat = Integer.parseInt(request.getParameter("fat"));

            log.info("id" + getid);
            log.info("kcal" + kcal);
            log.info("na" + na);
            log.info("tan" + tan);
            log.info("pro" + pro);
            log.info("fat" + fat);

            FoodDto fooddto = new FoodDto();
            fooddto.setId(getid);
            fooddto.setKcal(kcal);
            fooddto.setNa(na);
            fooddto.setTan(tan);
            fooddto.setPro(pro);
            fooddto.setFat(fat);

            Food food = Food.toEntity(fooddto);
            foodService.saveFood(food);

        } catch (Exception e){
            String msg = "수정을 실패했습니다" + e.getMessage();
            model.addAttribute("errorMessage", msg);
            return "/food/foodDetail";
        }

        log.info(this.getClass().getName() + "foodUpdate Stop!");

        return "main";
    }

    @PostMapping(value = "/delete")
    public String fooddelete(HttpServletRequest request, Model model){

        log.info(this.getClass().getName() + "foodDelete Start!");

        try {

            Long getid = Long.parseLong(request.getParameter("id"));
            log.info("id" + getid);

            foodService.deleteFood(getid);

        } catch (Exception e){
            String msg = "삭제를 실패했습니다" + e.getMessage();
            model.addAttribute("errorMessage", msg);
        }

        log.info(this.getClass().getName() + "foodDelete Stop!");

        return "main";
    }


}

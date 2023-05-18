package kopo.poly.controller;

import kopo.poly.config.CmmUtil;
import kopo.poly.dto.BorderDto;
import kopo.poly.entity.Border;
import kopo.poly.service.BorderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping(name = "/border")
@RequiredArgsConstructor
@Slf4j
public class BorderController {

    private final BorderService borderService;

    @GetMapping(value = "/list")
    public String borderlist(Model model){

        log.info(this.getClass().getName() + "borderList Start!");

        List<BorderDto> borderdtoList = borderService.getborderList();
        model.addAttribute("borderlist", borderdtoList);

        log.info(this.getClass().getName() + "borderList Stop!");

        return "/border/borderList";
    }

    @GetMapping(value = "/insert")
    public String borderinsert(Model model) {
        model.addAttribute("borderdto", new BorderDto());
        return "/border/borderInsert";
    }

    @PostMapping(value = "/insert")
    public String borderinsert(HttpServletRequest request, HttpSession session, Model model) {

        try {
            log.info(this.getClass().getName() + "borderCreate Start!");

            String memberEmail = CmmUtil.nvl((String) session.getAttribute("SS_MEMBER_EMAIL"));
            String title = CmmUtil.nvl(request.getParameter("title"));
            String content = CmmUtil.nvl(request.getParameter("content"));

            log.info("SS_MEMBER_EMAIL" + memberEmail);
            log.info("title" + title);
            log.info("content" + content);

            BorderDto borderdto = new BorderDto();
            borderdto.setMemberEmail(memberEmail);
            borderdto.setTitle(title);
            borderdto.setContent(content);

            Border border = Border.toEntity(borderdto);
            borderService.saveBorder(border);
        } catch (Exception e){

            model.addAttribute("errorMessage", e.getMessage());
            return "/border/borderInsert";
        }

        log.info(this.getClass().getName() + "borderInsert Stop!");

        return "/border/borderList";
    }

    @GetMapping(value = "/{borderid}")
    public String borderdetail(HttpServletRequest request, Model model){

        log.info(this.getClass().getName() + "borderdetail Star!");

        Long getid = Long.parseLong(request.getParameter("id"));
        log.info("Id" + getid);

        BorderDto borderdto = borderService.getborderDetail(getid);

        model.addAttribute("borderdtodetail", borderdto);

        log.info(this.getClass().getName() + "borderdetail Stop!");

        return "/border/borderDetail";
    }


}

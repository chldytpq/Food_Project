package kopo.poly.controller;

import kopo.poly.config.CmmUtil;
import kopo.poly.config.EncryptUtil;
import kopo.poly.dto.MemberDto;
import kopo.poly.entity.Member;
import kopo.poly.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RequestMapping("/member")
@Controller
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;

    @GetMapping(value = "/new")
    public String memberJoin(Model model) {
        model.addAttribute("memberdto", new MemberDto());
        return "member/memberJoin";
    }


    @PostMapping(value = "/new")
    public String memberJoin( Model model, HttpServletRequest request) throws Exception {

        log.info(this.getClass().getName() + ".memberJoin Start!");

//        if (bindingResult.hasErrors()) {
//            return "member/memberJoin";
//        }

        try {
            String email = CmmUtil.nvl(request.getParameter("email"));
            String password = CmmUtil.nvl(request.getParameter("password"));
            String name = CmmUtil.nvl(request.getParameter("name"));

            log.info("email :" + email);
            log.info("password :" + password);
            log.info("name :" + name);

            MemberDto memberdto = new MemberDto();
            memberdto.setEmail(email);
            memberdto.setPassword(EncryptUtil.encHashSHA256(password));
            memberdto.setName(name);

            Member member = Member.joinToEntity(memberdto);
            memberService.saveMember(member);
        } catch (IllegalStateException e) {
            log.info("errorMessage" + e.getMessage());
            model.addAttribute("errorMessage", e.getMessage());
            return "member/memberJoin";
        }
        log.info(this.getClass().getName() + ".memberJoin End!");

        return "member/memberLogin";
    }


    @GetMapping(value = "/login")
    public String MemberLogin() {
        return "/member/memberLogin";
    }

    @PostMapping(value = "/login")
    public String MemberLogin(HttpSession sesion, Model model, HttpServletRequest request) throws Exception {

        log.info(this.getClass().getName() + ".memberLogin start!");

        int res = 0;

        try {
            String email = CmmUtil.nvl(request.getParameter("email"));
            String password = CmmUtil.nvl(request.getParameter("password"));

            log.info("email :" + email);
            log.info("password :" + password);

            MemberDto memberdto = new MemberDto();
            memberdto.setEmail(email);
            memberdto.setPassword(EncryptUtil.encHashSHA256(password));

            Member member = Member.loginToEntity(memberdto);
            res = memberService.LoginCheck(member);

            if (res == 1) {
                sesion.setAttribute("SS_MEMBER_EMAIL", memberdto.getEmail());
            }

        } catch (IllegalStateException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "member/memberLogin";
        }
        log.info(this.getClass().getName() + ".memberLogin stop!");

        return "/main";

    }

    @PostMapping(value = "/logout")
    public String logout(HttpSession session, Model model) {

        log.info(this.getClass().getName() + ".logout Start!");

        session.setAttribute("SS_MEMBER_EMAIL", ""); // 세션 값 빈값으로 변경
        session.removeAttribute("SS_MEMBER_EMAIL"); // 세션 값 지우기

        model.addAttribute("Message", "로그아웃하셨습니다");

        log.info(this.getClass().getName() + ".logout End!");

        return "/member/memberLogin";

    }


}




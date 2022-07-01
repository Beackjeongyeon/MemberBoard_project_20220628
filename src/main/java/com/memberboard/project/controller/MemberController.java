package com.memberboard.project.controller;

import com.memberboard.project.dto.MemberDTO;
import com.memberboard.project.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;
    @GetMapping("/Signup1")
    public String signup() {
        return "/memberPages/Signup";
    }
    @PostMapping("/Signup-form")
    public String Signup(@ModelAttribute MemberDTO memberDTO) throws IOException{
        memberService.signup(memberDTO);
        System.out.println(memberDTO);
        return "redirect:/";
    }

    @GetMapping("/login1")
    public String login() {

        return "/memberPages/login";
    }
    @PostMapping ("/login")
    public String login(@ModelAttribute MemberDTO memberDTO, HttpSession session) {
        MemberDTO loginDTO= memberService.login(memberDTO);
        if(loginDTO !=null){
            session.setAttribute("loginid", loginDTO.getId());
            session.setAttribute("loginMemberId", loginDTO.getMemberId());
            session.setAttribute("loginPassword", loginDTO.getMemberPassword());
        return "redirect:/";
        }else{
            return "redirect:/memberPages/login";
        }
    }
    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/";
    }


    //id ajax 검사
    @PostMapping("/idCheck")
    public @ResponseBody String idCheck(@RequestParam("id") String id){
        String result = memberService.idCheck(id);
        System.out.println(result);
        return result;
    }

}

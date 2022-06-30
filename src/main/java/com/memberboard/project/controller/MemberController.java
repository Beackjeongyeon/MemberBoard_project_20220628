package com.memberboard.project.controller;

import com.memberboard.project.dto.MemberDTO;
import com.memberboard.project.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;
    @GetMapping("/Signup1")
    public String signup() {
        return "/boardPages/Signup";
    }
    @PostMapping("/Signup-form")
    public String Signup(@ModelAttribute MemberDTO memberDTO) throws IOException{
        memberService.signup(memberDTO);
        System.out.println(memberDTO);
        return "redirect:/";
    }
    @GetMapping("/login1")
    public String login() {
        return "/boardPages/login";
    }
    @PostMapping ("/login")
    public String login(@ModelAttribute MemberDTO memberDTO, HttpSession session) {
        memberService.login(memberDTO);
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

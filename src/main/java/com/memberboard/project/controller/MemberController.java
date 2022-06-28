package com.memberboard.project.controller;

import com.memberboard.project.dto.MemberDTO;
import com.memberboard.project.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

        return "/boardPages/pagelist";

    }

    @GetMapping("/login1")
    public String login() {
        return "/boardPages/login";
    }

}

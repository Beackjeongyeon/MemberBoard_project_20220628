package com.memberboard.project.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    @GetMapping("/pagelist1")
    public String pagelist(){
        return "/boardPages/pagelist";
    }
}
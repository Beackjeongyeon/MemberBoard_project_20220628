package com.memberboard.project.controller;

import com.memberboard.project.dto.BoardDTO;
import com.memberboard.project.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;
    //글작성
    @GetMapping("/save1")
    public String save(){
        return "/boardPages/save";
    }
    @PostMapping("/save")
    public String save(@ModelAttribute BoardDTO boardDTO, Model model)throws IOException {
        boardService.save(boardDTO);
        return "redirect:/boardPages/pagelist";
    }
    //글목록
    @GetMapping("/pagelist1")
    public String pagelist(){

        return "/boardPages/pagelist";
    }


}

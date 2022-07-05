package com.memberboard.project.controller;

import com.memberboard.project.common.PagingConst;
import com.memberboard.project.dto.BoardDTO;
import com.memberboard.project.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.awt.print.Pageable;
import java.io.IOException;
import java.util.List;

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
        return "redirect:/boardPages/list";
    }
    //글목록
    @GetMapping("/pagelist1")
    public String pagelist(@ModelAttribute BoardDTO boardDTO, Model model){
        List<BoardDTO> boardDTOList = boardService.findAll();
        model.addAttribute("boardList", boardDTOList);
        return "/boardPages/detail";
    }
    @GetMapping
    public String paging(@PageableDefault(page = 1) Pageable pageable, Model model) {
        Page<BoardDTO> boardList = boardService.paging(pageable);
        model.addAttribute("boardList", boardList);
        int startPage = (((int) (Math.ceil((double) pageable.getPageNumber() / PagingConst.BLOCK_LIMIT))) - 1) * PagingConst.BLOCK_LIMIT + 1;
        int endPage = ((startPage + PagingConst.BLOCK_LIMIT - 1) < boardList.getTotalPages()) ? startPage + PagingConst.BLOCK_LIMIT - 1 : boardList.getTotalPages();
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        return "boardPages/list";
    }


}

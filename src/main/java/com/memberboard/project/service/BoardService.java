package com.memberboard.project.service;


import com.memberboard.project.dto.BoardDTO;
import com.memberboard.project.entity.BoardEntity;
import com.memberboard.project.entity.MemberEntity;
import com.memberboard.project.repository.BoardRepository;
import com.memberboard.project.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;


    public Long save(BoardDTO boardDTO)throws IOException {
        MultipartFile boardFile = boardDTO.getBoardFile();
        String boardFileName = boardFile.getOriginalFilename();
        boardFileName =System.currentTimeMillis()+"_"+boardFileName;
        String savePath = "D:\\springboot_img" + boardFileName;
        if(!boardFile.isEmpty()){
            boardFile.transferTo(new File(savePath));
        }
        boardDTO.setBoardFileName(boardFileName);
        Optional<MemberEntity> optionalMemberEntity=
                memberRepository.findByMemberId(boardDTO.getBoardWriter());
        if(optionalMemberEntity.isPresent()){
            MemberEntity memberEntity = optionalMemberEntity.get();
            Long saveId = boardRepository.save(BoardEntity.entitySave(boardDTO.memberEntity)).getId();
        }
    }
}

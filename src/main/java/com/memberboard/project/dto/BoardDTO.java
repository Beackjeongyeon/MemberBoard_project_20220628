package com.memberboard.project.dto;

import com.memberboard.project.entity.BoardEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardDTO {
    private Long id;
    private String boardTitle;
    private String boardWriter;
    private String boardContents;
    private int boardHits;
    private LocalDateTime boardCreatedDate;
    private MultipartFile boardFile;
    private String boardFileName;

    public BoardDTO(String boardTitle, String boardWriter, String boardContents, int boardHits, LocalDateTime boardCreatedDate, MultipartFile boardFile, String boardFileName) {
        this.boardTitle = boardTitle;
        this.boardWriter = boardWriter;
        this.boardContents = boardContents;
        this.boardHits = boardHits;
        this.boardCreatedDate = boardCreatedDate;
        this.boardFile = boardFile;
        this.boardFileName = boardFileName;
    }
    public static BoardDTO toFind(BoardEntity boardEntity){
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setId(boardEntity.getId());
        boardDTO.setBoardTitle(boardEntity.getBoardTitle());
        boardDTO.setBoardWriter(boardEntity.getBoardWriter());
        boardDTO.setBoardContents(boardEntity.getBoardContents());
        boardDTO.setBoardHits(0);
        boardDTO.setBoardFileName(boardEntity.getBoardFileName());
        return boardDTO;
    }
}

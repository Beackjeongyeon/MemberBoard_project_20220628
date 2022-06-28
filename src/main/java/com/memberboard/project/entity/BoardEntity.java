package com.memberboard.project.entity;

import com.memberboard.project.dto.BoardDTO;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.lang.reflect.Member;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter@Setter
@Table(name = "board_table")
public class BoardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column( name="board_id")
    private Long id;

    @Column(length = 30 , nullable = false)
    private String boardTitle;

    @Column(length = 30)
    private String boardWriter;

    @Column(length = 500)
    private String boardContents;

    @Column
    private int boardHits;

    @CreationTimestamp
    @Column(insertable = false)
    private LocalDateTime boardCreatedDate;

    @Column(length = 50)
    private String boardFileName;

    //게시글과 댓글작성자는 회원테이블의 아이디를 참조함
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")

    private MemberEntity memberEntity;
    //회원과 게시글이 연관관계를 맺은 후
    public static BoardEntity toSaveEntity(BoardDTO boardDTO, MemberEntity memberEntity){
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.setBoardTitle(boardDTO.getBoardTitle());
        boardEntity.setBoardWriter(memberEntity.getMemberId());//회원 아이디를 작성자로함
        boardEntity.setBoardContents(boardDTO.getBoardContents());
        boardEntity.setBoardHits(0);
        boardEntity.setMemberEntity(memberEntity);
        return boardEntity;
    }
}

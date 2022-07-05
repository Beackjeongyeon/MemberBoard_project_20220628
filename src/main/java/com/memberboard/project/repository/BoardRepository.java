package com.memberboard.project.repository;

import com.memberboard.project.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BoardRepository extends JpaRepository<BoardEntity, Long>{
    @Modifying
    @Query(value = "update BoardEntity b set b.boardHits = b.boardHits +1 where b.id=: id")
    void boardHits(Long id);

    List<BoardEntity> findByBoardTitleContainingOrBoardWriterContaining(String q1, String q2);



}

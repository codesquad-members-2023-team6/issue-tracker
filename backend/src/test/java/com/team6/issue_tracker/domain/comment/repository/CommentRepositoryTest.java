package com.team6.issue_tracker.domain.comment.repository;

import com.team6.issue_tracker.domain.comment.domain.Comment;
import com.team6.issue_tracker.domain.comment.repository.CommentRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@DataJdbcTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CommentRepositoryTest {

    @Autowired
    CommentRepository commentRepository;

    @Test
    @DisplayName("Comment Id로 comment를 찾을 수 있다.")
    void findById() throws Exception {
        //given
        Comment comment = commentRepository.findById(1L).orElseThrow();

        //when
        log.debug("comment = {}", comment);

        //then
        assertThat(comment.getContents().getContents()).isEqualTo("test comment");
    }

    @Test
    @DisplayName("Comment 를 Repository에 저장할 수 있다.")
    void saveComment() throws Exception {
        //given
        Comment testComment = Comment.newComment("testComment", 1L, 2L);

        //when
        Comment save = commentRepository.save(testComment);

        //then
        assertThat(save.getContents().getContents()).isEqualTo("testComment");
    }

    @Test
    @DisplayName("Comment 를 Repository에 저장하면, Repository Count가 1 증가한다.")
    void saveAtRepository() throws Exception {
        //given
        long count = commentRepository.count();

        //when
        Comment testComment = Comment.newComment("testComment", 1L, 2L);
        commentRepository.save(testComment);

        //then
        assertThat(commentRepository.count()).isEqualTo(count + 1);
    }
    
    @Test
    @DisplayName("코멘트 아이디로 코멘트를 soft delete할 수 있다.")
    public void deleteComment() throws Exception{
        //given
        long commentId = 1L;
        
        //when
        commentRepository.softDeleteById(commentId);
    
        //then
        assertThat(commentRepository.findById(commentId).orElseThrow().getIsDeleted()).isTrue();
    }
    
    @Test
    @DisplayName("삭제된 것만 제외하고 findAll로 코멘트를 조회할 수 있다.")
    public void softDeleteFindAll() throws Exception{
        //given
        List<Comment> all = commentRepository.findAll();

        //when
    
        //then
        assertThat(all).hasSize(17);
    }
}

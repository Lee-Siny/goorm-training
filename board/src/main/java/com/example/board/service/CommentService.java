package com.example.board.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.board.model.entity.Board;
import com.example.board.model.request.CommentPostRequest;
import com.example.board.model.response.BoardResponse;
import com.example.board.repository.BoardRepository;
import com.example.board.repository.CommentRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;

    @Transactional
    public BoardResponse writeComment(CommentPostRequest request) {
        Optional<Board> boardOptional = boardRepository.findBoardWithCommentsByBoardNo(request.getBoardNo());
        Board board = boardOptional.orElseThrow(() -> new RuntimeException("존재하지 않는 게시글입니다!"));

        //board에 댓글 추가
        board.addComment(request.getCommentBody());
        boardRepository.save(board);

        return BoardResponse.from(board);
    } //writeComment()
} //class

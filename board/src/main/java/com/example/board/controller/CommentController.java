package com.example.board.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.board.model.request.CommentPostRequest;
import com.example.board.model.response.BoardResponse;
import com.example.board.service.CommentService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    //댓글 등록
    @PostMapping("comment")
    public BoardResponse writeComment(
            @RequestBody CommentPostRequest commentPostRequest) {
        return commentService.writeComment(commentPostRequest);
    } // writeComment

} //class
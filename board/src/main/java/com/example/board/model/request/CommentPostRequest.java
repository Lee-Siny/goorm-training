package com.example.board.model.request;

import lombok.Data;

@Data
public class CommentPostRequest {
    private Long boardNo;
    private String commentBody;
} //class

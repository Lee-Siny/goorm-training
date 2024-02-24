package com.example.board.model.response;

import com.example.board.model.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CommentResponse {
    private Long commentNo;
    private String body;

    // static factory method
    // from : 매개변수를 하나 받아서 해당 타입의 인스턴스를 반환하는 형변환 메서드
    public static CommentResponse from(Comment comment) {
        return new CommentResponse(
                comment.getCommentNo(),
                comment.getBody()
        );
    } //from
} //class


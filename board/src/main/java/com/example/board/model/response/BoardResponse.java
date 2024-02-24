package com.example.board.model.response;

import java.util.List;
import java.util.stream.Collectors;

import com.example.board.model.DeleteStatus;
import com.example.board.model.entity.Board;
import lombok.AllArgsConstructor;
import lombok.Data;

//게시물 단건
@Data
@AllArgsConstructor
public class BoardResponse {
    private Long boardNo;
    private String title;
    private String body;
    private DeleteStatus deleteStatus;
    private List<CommentResponse> comments;

    // static factory method
    // from : 매개변수를 하나 받아서 해당 타입의 인스턴스를 반환하는 형변환 메서드
    // board entity-> board response
    public static BoardResponse from(Board board) {
        return new BoardResponse(
                board.getBoardNo(),
                board.getTitle(),
                board.getBody(),
                board.getDeleteStatus(),
                board.getComments().stream().map(CommentResponse::from).collect(Collectors.toList())
        );
    } //from
} //class

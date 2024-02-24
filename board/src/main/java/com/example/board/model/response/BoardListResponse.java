package com.example.board.model.response;

import com.example.board.model.entity.Board;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BoardListResponse {
    private Long boardNo;
    private String title;

    // static factory method
    // from : 매개변수를 하나 받아서 해당 타입의 인스턴스를 반환하는 형변환 메서드
    public static BoardListResponse from(Board board) {
        return new BoardListResponse(
                board.getBoardNo(),
                board.getTitle()
        );
    } //from
} //class

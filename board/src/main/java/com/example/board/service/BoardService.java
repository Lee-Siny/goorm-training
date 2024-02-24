package com.example.board.service;


import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.board.model.DeleteStatus;
import com.example.board.model.entity.Board;
import com.example.board.model.request.BoardDeleteRequest;
import com.example.board.model.request.BoardPostRequest;
import com.example.board.model.response.BoardListResponse;
import com.example.board.model.response.BoardResponse;
import com.example.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    //게시글 등록
    @Transactional
    public BoardResponse writeBoard(BoardPostRequest request) {
        Board board = new Board();
        board.setTitle(request.getTitle());
        board.setBody(request.getBody());
        board.setDeleteStatus(DeleteStatus.ACTIVE);

        return BoardResponse.from(boardRepository.save(board));
    } //writeBoard()

    public List<BoardListResponse> searchBoardList(int page, int pageSize) {
        return boardRepository.findAllByDeleteStatus(
                DeleteStatus.ACTIVE,
                PageRequest.of(page, pageSize, Sort.by(Sort.Direction.DESC, "boardNo"))
        ).map(BoardListResponse::from).toList();
    } //searchBoardList()

    public BoardResponse searchBoard(Long boardNo) {
        return boardRepository.findBoardWithCommentsByBoardNo(boardNo)
                .map(BoardResponse::from)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 게시글입니다."));
    } //searchBoard()

    @Transactional
    public String deleteBoard(BoardDeleteRequest request) {
        //예외
        Optional<Board> boardOptional = boardRepository.findById(request.getBoardNo());
        Board board = boardOptional.orElseThrow(() -> new RuntimeException("존재하지않는 게시물!"));

        boardRepository.delete(board);
        //게시물 삭제시 코멘트 삭제도 필요

        return "OK";
    } //deleteBoard()
} //class

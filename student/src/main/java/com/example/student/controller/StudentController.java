package com.example.student.controller;

import com.example.student.entity.ApiResponse;
import com.example.student.entity.ErrorCode;
import com.example.student.exception.CustomException;
import com.example.student.exception.InputRestriction;
import com.example.student.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;

    //1. 이름과 성적을 입력받아 저장
    //POST
    @PostMapping("/student")
    public ApiResponse add(
            @RequestParam("name") String name,
            @RequestParam("grade") int grade
    ) {
//        하위과제 테스트
//        try {
//          test(name, grade);
//        } catch (Exception e) {
//          throw new CustomException(ErrorCode.BAD_REQUEST, "custom custom exception", null);
//        } //try-catch

        if (grade >= 6) {
            throw new CustomException(ErrorCode.BAD_REQUEST, "grade는 6 이상을 입력할 수 없습니다.", new InputRestriction(6));
        } //if

        return makeResponse(studentService.addStudent(name, grade));
    } //add


    // 2. 전체 학생을 조회하는 API
    @GetMapping("/students")
    public ApiResponse getAll() {
        return makeResponse(studentService.getAll());
    } //getAll


    // 3. 특정 성적을 입력받아, 해당 성적의 학생들을 조회
    @GetMapping("/students/{grade}")
    public ApiResponse getGradeStudents(
            @PathVariable("grade") int grade
    ) {
        return makeResponse(studentService.getGradeStudent(grade));
    } //getGradeStudents()


    //복수의 result를 가지고 있는경우 응답 내려주기
    public <T> ApiResponse<T> makeResponse(List<T> results) {
        return new ApiResponse<>(results);
    } //makeResponse()

    //단수의 result를 내려줌
    public <T> ApiResponse<T> makeResponse(T result) {
        return makeResponse(Collections.singletonList(result));
    } //makeResponse()

    // Controller가 많아지면 exceptionHandler만 모아놓은 핸들러클래스를 따로 만들어서 그 클래스에 @RestControllerAdvice
    // -> 매 controller마다 exception handler를 걸어주지 않아도 어드바이스를 통해서 자동으로 걸림
    // Spring AOP, JDK Dynamic Proxy 공부
    @ExceptionHandler(CustomException.class)
    public ApiResponse customExceptionHandler(CustomException customException) {
        return new ApiResponse(customException.getErrorCode().getCode(),
                customException.getErrorCode().getMessage(),
                customException.getData());
    } //customExceptionHandler()

//    하위과제 테스트
//  public ApiResponse test(String name, int grade) {
//    throw new CustomException(ErrorCode.BAD_REQUEST, "[inner] grade는 6이상 입력 불가!", new InputRestriction(6));
//  } //test

} //class
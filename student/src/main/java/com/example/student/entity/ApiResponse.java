package com.example.student.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

public class ApiResponse<T> {
    private final Status status;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Metadata metadata;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<T> results;

    //error response
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Object data;

    //정상응답 생성자
    public ApiResponse(List<T> results) {
        this.status = new Status(2000, "OK");
        this.metadata = new Metadata(results.size());
        this.results = results;
    } //Success Constructor

    //에러응답 생성자
    public ApiResponse(int code, String message, Object data) {
        this.status = new Status(code, message);
        this.data = data;
    } //Error Constructor

    //static inner class
    // 내부 ->외부 접근 방지
    // 메모리 관리
    @Getter
    @AllArgsConstructor
    private static class Status {
        private int code;
        private String message;
    } //inner class

    @Getter
    @AllArgsConstructor
    private static class Metadata {
        private int resultCount = 0;
    } //inner class

} //class

//    정상응답
//    {
//        "status": {
//            "code":2000,
//            "message":"OK"
//        },
//        metadata:{
//            "resultCount": 1
//        },
//        "results":[
//            {
//                "name":"kim",
//                "grade":"1"
//            }
//        ]
//    }
//
//
//    {
//        "status":{
//            "code":5000,
//            "message":"grade는 6 이상을 입력 할 수 없습니다"
//        },
//        "data":{
//                "inputRestriction": {
//                "maxGrade":"6"
//            }
//        }
//    }

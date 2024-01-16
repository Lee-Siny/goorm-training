package com.example.student.exception;

import com.example.student.entity.ErrorCode;
import lombok.Getter;
import org.springframework.util.StringUtils;

import java.util.AbstractMap;
import java.util.Map;

public class CustomException extends  RuntimeException {
    @Getter
    private final ErrorCode errorCode;

    private String message;
    //inputRestriction 이라는 특정 데이터를 나타내는 값을 가지고 있으니까
    //String Object
    //			"data":{
    //				    "inputRestriction": {
    //					    "maxGrade":"6"
    //				    }
    //			}

    @Getter
    private Map.Entry<String, Object> data;

    @Override
    public String getMessage() {
        if (StringUtils.hasLength(this.message)) {
            return this.message;
        } //if
        return errorCode.getMessage();
    } //getMessage()

    public CustomException(ErrorCode errorCode, String message, Object data) {
        this.errorCode = errorCode;
        this.message = message;

        // 하위과제 null 처리
        if (data != null) {
            this.data = new AbstractMap.SimpleEntry<>(data.getClass().getSimpleName(), data);
        } else {
            this.data = null;
        } //if-else
    } //CustomException Constructor

} //class

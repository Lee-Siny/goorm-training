package com.goorm.tricount.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member {
    // 멤버 저장을 위해 필요
    private Long id;
    private String loginId;
    private String name;

    @JsonIgnore
    private String password;
} //class

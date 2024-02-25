package com.goorm.tricount.controller;

import com.goorm.tricount.Service.MemberService;
import com.goorm.tricount.dto.LoginRequest;
import com.goorm.tricount.dto.SignupRequest;
import com.goorm.tricount.model.Member;
import com.goorm.tricount.util.TricountApiConst;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AuthController {
    private final MemberService memberService;

    // 회원가입
    @PostMapping("/signup")
    public ResponseEntity<Member> signup(@Valid @RequestBody SignupRequest request) {
        Member member = Member.builder()
                .loginId(request.getLoginId())
                .password(request.getPassword())
                .name(request.getName())
                .build();
        return new ResponseEntity<>(memberService.signup(member), HttpStatus.OK);
    } //signup

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<Void> login(
            @Valid @RequestBody LoginRequest loginRequest,
            HttpServletResponse response) {
        Member loginMember = memberService.login(loginRequest.getLoginId(), loginRequest.getPassword());

        // 로그인 성공처리 - 쿠키 생성
        Cookie idCookie = new Cookie(TricountApiConst.LOGIN_MEMBER_COOKIE, String.valueOf(loginMember.getId()));
        response.addCookie(idCookie);

        return new ResponseEntity<>(HttpStatus.OK);
    } //login

    // 로그아웃
    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletResponse response) {
        //cookie 없애줌
        Cookie cookie = new Cookie(TricountApiConst.LOGIN_MEMBER_COOKIE, null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return new ResponseEntity<>(HttpStatus.OK);
    } //logout
} //class

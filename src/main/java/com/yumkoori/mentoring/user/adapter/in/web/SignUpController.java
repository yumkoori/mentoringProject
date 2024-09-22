package com.yumkoori.mentoring.user.adapter.in.web;

import com.yumkoori.mentoring.common.ResultDto;
import com.yumkoori.mentoring.common.WebAdapter;
import com.yumkoori.mentoring.user.adapter.in.web.dto.SignupRequestForEmailDto;
import com.yumkoori.mentoring.user.application.port.in.RegisterUserUseCase;
import com.yumkoori.mentoring.user.application.port.in.command.RegisterUserCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class SignUpController {

    private final RegisterUserUseCase registerUserUseCase;

    @PostMapping("/auth/signup/email")
    public ResponseEntity<ResultDto<String>> signup_Email(@RequestBody SignupRequestForEmailDto request) {
        RegisterUserCommand registerCommand = RegisterUserCommand.builder()
                .email(request.getEmail())
                .password(request.getPassword())
                .nick(request.getNick())
                .build();

        registerUserUseCase.registerUser(registerCommand);

        ResultDto<String> result = new ResultDto<>(200, "회원가입 완료", registerCommand.getNick());
        return new ResponseEntity<>(result, HttpStatus.CREATED);

    }

}

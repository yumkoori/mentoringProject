package com.yumkoori.mentoring.user.adapter.in.web;

import com.yumkoori.mentoring.common.ResultDto;
import com.yumkoori.mentoring.common.WebAdapter;
import com.yumkoori.mentoring.user.adapter.in.web.dto.LoginRequestDto;
import com.yumkoori.mentoring.user.adapter.in.web.dto.LoginResponseDto;
import com.yumkoori.mentoring.user.application.port.in.LoginUseCase;
import com.yumkoori.mentoring.user.application.port.in.TokenServiceUseCase;
import com.yumkoori.mentoring.user.application.port.in.command.CreateAccessTokenCommand;
import com.yumkoori.mentoring.user.application.port.in.command.LoginCommand;
import com.yumkoori.mentoring.user.application.port.out.PasswordEncoderPort;
import com.yumkoori.mentoring.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RequiredArgsConstructor
@RestController
public class LoginController {

    private final LoginUseCase loginUseCase;
    private final TokenServiceUseCase tokenServiceUseCase;

    @PostMapping("/auth/login-email")
    public ResponseEntity<ResultDto<LoginResponseDto>> login(@RequestBody LoginRequestDto request) {

        LoginCommand loginCommand = new LoginCommand(request.getEmail(), request.getPassword());

        //user 유효성 검증
        User user = loginUseCase.authenticate(loginCommand);

        //jwt 토큰 AccessToken, RefreshToken 생성 후 AccessToken, RefreshToken 둘다 응답
        CreateAccessTokenCommand createAccessTokenCommand = new CreateAccessTokenCommand(user);

        String newAccessToken = tokenServiceUseCase.createNewAccessToken(createAccessTokenCommand);
        String newRefreshToken = tokenServiceUseCase.createNewRefreshToken(createAccessTokenCommand);

        System.out.println(newRefreshToken);
        System.out.println(newRefreshToken);

        ResultDto<LoginResponseDto> result =
                new ResultDto<>(
                        200,
                        "새로운 accessToken 발급 완료",
                        new LoginResponseDto(newAccessToken, newRefreshToken));

        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }
}

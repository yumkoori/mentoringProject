package com.yumkoori.mentoring.user.adapter.in.web;

import com.yumkoori.mentoring.common.ResultDto;
import com.yumkoori.mentoring.common.WebAdapter;
import com.yumkoori.mentoring.user.adapter.in.web.dto.CreateAccessTokenRequestDto;
import com.yumkoori.mentoring.user.adapter.in.web.dto.CreateAccessTokenResponseDto;
import com.yumkoori.mentoring.user.application.port.in.TokenServiceUseCase;
import com.yumkoori.mentoring.user.application.port.in.command.ReCreateAccessTokenCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@WebAdapter
@RequiredArgsConstructor
public class TokenApiController {

    private final TokenServiceUseCase tokenServiceUseCase;

    @PostMapping("/auth/token")
    public ResponseEntity<ResultDto<CreateAccessTokenResponseDto>> createAccessToken_withRefreshToken(@RequestBody
            CreateAccessTokenRequestDto request) {
        ReCreateAccessTokenCommand command = new ReCreateAccessTokenCommand(request.getRefreshToken());

        String newAccessToken = tokenServiceUseCase.ReCreateNewAccessToken(command);

        ResultDto<CreateAccessTokenResponseDto> result =
                new ResultDto<>(
                        200,
                        "새로운 accessToken 발급 완료",
                        new CreateAccessTokenResponseDto(newAccessToken));

        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }
}

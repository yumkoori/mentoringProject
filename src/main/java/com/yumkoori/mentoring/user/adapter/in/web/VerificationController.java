package com.yumkoori.mentoring.user.adapter.in.web;

import com.yumkoori.mentoring.common.Adapter;
import com.yumkoori.mentoring.common.ResultDto;
import com.yumkoori.mentoring.user.adapter.in.web.dto.CheckVerificationDto;
import com.yumkoori.mentoring.user.adapter.in.web.dto.EmailVerificationRequestDto;
import com.yumkoori.mentoring.user.adapter.in.web.dto.TokenResponseDto;
import com.yumkoori.mentoring.user.adapter.out.email.SendEmailProvider;
import com.yumkoori.mentoring.user.application.port.in.VerificationUseCase;
import com.yumkoori.mentoring.user.application.port.in.command.CheckVerificationCommand;
import com.yumkoori.mentoring.user.application.port.in.command.RequestVerificationCommand;
import com.yumkoori.mentoring.user.domain.EmailVerification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Adapter
@RestController
@RequiredArgsConstructor
@Slf4j
public class VerificationController {

    private final VerificationUseCase verifiCationUseCase;
    private final SendEmailProvider sendEmailProvider;

    @PostMapping(value = "/auth/verify-email")
    public ResponseEntity<ResultDto<Boolean>> sendVerification(@RequestBody EmailVerificationRequestDto request) {

        log.info("진입 성공");
        RequestVerificationCommand command = new RequestVerificationCommand(request.getEmail());

        EmailVerification emailVerification = verifiCationUseCase.requestEmailVerification(command);
        log.info("verification = {}", emailVerification.getVerification());

        boolean isSendSuccess = sendEmailProvider.sendVerificationMail(emailVerification);

        log.info("isSendSuccess = {}", isSendSuccess);
        ResultDto<Boolean> result = new ResultDto<>(200, "인증메일 전송 완료", isSendSuccess);

        return new ResponseEntity<>(result, HttpStatus.OK);

    }

    @PostMapping(value = "/auth/verify-email-check")
    public ResponseEntity<ResultDto<TokenResponseDto>> checkVerification(@RequestBody
            CheckVerificationDto checkRequest) {

        CheckVerificationCommand checkVerificationCommand = new CheckVerificationCommand(
                checkRequest.getVerificationCode(), checkRequest.getEmail());

        boolean verificationCheckResult = verifiCationUseCase.checkEmailVerification(checkVerificationCommand);

        //if check결과가 true면, jwt 토큰 생성 요청
        //if false면 해당 코드가 일치하지 않습니다. 재요청

        //임시 토큰 번호
        TokenResponseDto tokenResponseDto = new TokenResponseDto("45221564");

        ResultDto<TokenResponseDto> result = new ResultDto<>(200, "메일 인증이 완료되었습니다.", tokenResponseDto);
        return new ResponseEntity<>(result, HttpStatus.OK);

    }

}

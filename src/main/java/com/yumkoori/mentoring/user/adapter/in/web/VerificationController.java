package com.yumkoori.mentoring.user.adapter.in.web;

import com.yumkoori.mentoring.common.Adapter;
import com.yumkoori.mentoring.common.ResultDto;
import com.yumkoori.mentoring.user.adapter.in.web.dto.EmailVerificationRequestDto;
import com.yumkoori.mentoring.user.adapter.out.email.SendEmailProvider;
import com.yumkoori.mentoring.user.application.port.in.VerificationUseCase;
import com.yumkoori.mentoring.user.application.port.in.command.RequestVerificationCommand;
import com.yumkoori.mentoring.user.domain.EmailVerification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

}

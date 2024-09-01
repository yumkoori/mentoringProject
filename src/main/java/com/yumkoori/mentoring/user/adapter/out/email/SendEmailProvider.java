package com.yumkoori.mentoring.user.adapter.out.email;

import com.yumkoori.mentoring.common.Adapter;
import com.yumkoori.mentoring.user.domain.EmailVerification;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

@Adapter
@RequiredArgsConstructor
@Slf4j
public class SendEmailProvider {

    private final JavaMailSender javaMailSender;

    private final String SUBJECT = "[멘토링 서비스] 인증 메일 입니다.";

    public boolean sendVerificationMail(EmailVerification emailVerification) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);

            String messageContent = getVerificationMessage(emailVerification.getVerifyCation());

            messageHelper.setTo(emailVerification.getEmail());
            messageHelper.setSubject(SUBJECT);
            messageHelper.setText(messageContent, true);

            javaMailSender.send(message);

        } catch (Exception exception) {
            log.error("sendMailError", exception);
            return false;
        }
        return true;
    }

    private String getVerificationMessage(String verification) {
        String verificationMessage = "";
        verificationMessage += "<h1 style='text-align: center;'>[멘토링 서비스] 인증메일</h1>";
        verificationMessage += "h3 style = 'text-align: center;'>인증코드 : " +
                "<strong style='font-size: 32px; letter-spacing:8px;'>" + verification + "</strong></h3>";

        return verificationMessage;
    }


}

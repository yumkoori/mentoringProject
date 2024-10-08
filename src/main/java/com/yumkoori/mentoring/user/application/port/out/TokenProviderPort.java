package com.yumkoori.mentoring.user.application.port.out;

import com.yumkoori.mentoring.user.domain.User;
import io.jsonwebtoken.Claims;
import java.time.Duration;
import java.util.Date;
import org.springframework.security.core.Authentication;

public interface TokenProviderPort {
    String generateToken(User user, Duration expiredAt);
    boolean validToken(String token);
    Authentication getAuthentication(String token);
    Long getUserId(String token);
}

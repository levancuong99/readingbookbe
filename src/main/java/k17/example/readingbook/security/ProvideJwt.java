package k17.example.readingbook.security;

import k17.example.readingbook.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import java.util.Date;

@Component
public class ProvideJwt {
    @Value("secret")
    private String jwtSecret;

    @Value("64800000")
    private String jwtExpirationInMs;

    public String generateTokenForUser(User auth) {
        return JWT.create()
                .withSubject(Integer.toString(auth.getUserId()))
                .withClaim("id", auth.getUserId())
                .withExpiresAt(new Date(System.currentTimeMillis()  + Integer.parseInt(jwtExpirationInMs)))
                .sign(Algorithm.HMAC512(jwtSecret.getBytes()));
    }

    public Integer getUserIdFromJWT (String token) {
        String id = JWT
                .require(Algorithm.HMAC512(jwtSecret.getBytes()))
                .build()
                .verify(token)
                .getSubject();

        return Integer.parseInt(id);
    }
}

package io.github.ifpb.pw2.apigateway.service.jwt;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.stream.Collectors;

@Service
@Slf4j
public class TokenProvider {

    @Value("${jwt.secret}")
    private String jwtSecret;

    public String createToken(Authentication authentication) {

        User user = (User) authentication.getPrincipal();

        String authorities = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(","));

        return Jwts.builder()
                .setSubject(user.getUsername())
                .setExpiration(Date.from(ZonedDateTime.now().plusHours(1).toInstant()))
                .setIssuedAt(Date.from(ZonedDateTime.now().toInstant()))
                .claim("auth", authorities)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();

    }

    public Authentication getAuthentication(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();

        // Collecting authorities
        Collection<? extends GrantedAuthority> authorities = Arrays
                .stream(claims.get("auth").toString().split(",")).parallel()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toSet());

        UserDetails principal = new User(claims.getSubject(), "", authorities);
        return new UsernamePasswordAuthenticationToken(principal, token, authorities);
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            log.info("Invalid JWT signature.");
        } catch (MalformedJwtException e) {
            log.info("Invalid JWT token.");
        } catch (ExpiredJwtException e) {
            log.info("Expired JWT token.");
        } catch (UnsupportedJwtException e) {
            log.info("Unsupported JWT token.");
        } catch (IllegalArgumentException e) {
            log.info("JWT token compact of handler are invalid.");
        }
        return false;
    }

}

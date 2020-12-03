package com.ikubinfo.security.jwt;

import java.time.ZonedDateTime;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.ikubinfo.security.exception.InvalidTokenException;
import com.ikubinfo.security.service.UserPrincipal;
import com.ikubinfo.utils.AppProperties;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
@PropertySource(value = AppProperties.JWTPROVIDER)
public class JwtProvider {

	@Value("${jwt.secret}")
	private String jwtSecret;

	@Value("${jwt.validFor}")
	private int jwtExpiration;

	public String generateJwtToken(Authentication authentication) {
		UserPrincipal userPrinciple = (UserPrincipal) authentication.getPrincipal();
		ZonedDateTime issuedDate = ZonedDateTime.now();
		ZonedDateTime expirationDate = calculateExpirationDate(issuedDate);

		return Jwts.builder().setSubject(userPrinciple.getUsername()).setIssuedAt(new Date())
				.setExpiration(Date.from(expirationDate.toInstant())).signWith(SignatureAlgorithm.HS512, jwtSecret)
				.compact();
	}

	private ZonedDateTime calculateExpirationDate(ZonedDateTime issuedDate) {
		return issuedDate.plusSeconds(jwtExpiration);
	}
	
	public boolean validateJwtToken(String authToken) throws InvalidTokenException {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            throw new InvalidTokenException("Invalid JWT signature");

        } catch (MalformedJwtException e) {
            throw new InvalidTokenException("Invalid JWT token");

        } catch (ExpiredJwtException e) {
            throw new InvalidTokenException("Expired JWT token");

        } catch (UnsupportedJwtException e) {
            throw new InvalidTokenException("Unsupported JWT token");
        } catch (IllegalArgumentException e) {
            throw new InvalidTokenException("JWT claims string is empty -> Message: {}");
        }

    }

    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody().getSubject();
    }
}

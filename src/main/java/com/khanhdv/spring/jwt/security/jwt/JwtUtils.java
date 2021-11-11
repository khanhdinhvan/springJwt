package com.khanhdv.spring.jwt.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtils {

  @Value("${app.jwtSecret}")
  private String JWT_TOKEN_SECRET;

  @Value("${app.jwtExpirationMs}")
  private int JWT_TOKEN_VALIDITY;

  //use the secret key to get the information from token
  private Claims getAllClaimsFromToken(String token) {
    return Jwts.parser().setSigningKey(JWT_TOKEN_SECRET).parseClaimsJws(token).getBody();
  }

  private <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
    final Claims claims = getAllClaimsFromToken(token);
    return claimsResolver.apply(claims);
  }

  //get expiration date from jwt token
  private Date getExpirationDateFromToken(String token) {
    return getClaimFromToken(token, Claims::getExpiration);
  }

  //check if the token has expired
  private Boolean isTokenExpired(String token) {
    final Date expiration = getExpirationDateFromToken(token);
    System.out.println("expiration" + expiration);
    System.out.println("Date" + new Date());
    return expiration.before(new Date());
  }

  //while creating the token -
  //1. Define  claims of the token, like Issuer, Expiration, Subject, and the ID
  //2. Sign the JWT using the HS512 algorithm and secret key.
  //3. According to JWS Compact Serialization(https://tools.ietf.org/html/draft-ietf-jose-json-web-signature-41#section-3.1)
  //   compaction of the JWT to a URL-safe string
  private String doGenerateToken(Map<String, Object> claims, String subject) {
    return Jwts.builder()
            .setClaims(claims)
            .setSubject(subject)
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
            .signWith(SignatureAlgorithm.HS512, JWT_TOKEN_SECRET)
            .compact();
  }

  //get username from jwt token
  public String getUsernameFromToken(String token) {
    return getClaimFromToken(token, Claims::getSubject);
  }

  public String generateToken(UserDetails userDetails) {
    Map<String, Object> claims = new HashMap<>();
    return doGenerateToken(claims, userDetails.getUsername());
  }

  public Boolean validateToken(String token, UserDetails userDetails) {
    final String username = getUsernameFromToken(token);
    return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
  }

}

package com.example.demo.model;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;

public class MyToken {

  private String secretcode = "myName";

  public String generateToken(int idSociete) {
    String jwtToken = null;
    jwtToken = Jwts.builder().signWith(SignatureAlgorithm.HS256, secretcode)
        .setIssuedAt(new Date(System.currentTimeMillis()))
        .setExpiration(new Date(System.currentTimeMillis() + 200000)).claim("idSociete", idSociete).compact();
    return jwtToken;
  }

  public Date expirationdateToken(String token) {
    return Jwts.parser().setSigningKey(secretcode)
        .parseClaimsJws(token).getBody().getExpiration();
  }

  public Claims testTokenClaims(String token) throws Exception {
    Claims cl = null;
    if (token != null) {
      try {
        cl = Jwts.parser().setSigningKey(secretcode)
            .parseClaimsJws(token).getBody();
      } catch (Exception e) {
        throw new Exception("Token Expired");
      }
    } else {
      throw new Exception("Token Invalid");
    }
    return cl;
  }

}
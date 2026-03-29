package com.github.arieljorge.sgto.service;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.Map;
import java.util.function.Function;

public interface JwtService {
    String gerarToken(UserDetails userDetails);
    String gerarToken(Map<String, Object> extraClaims, UserDetails userDetails);
    String extrairUsername(String token);
    boolean isTokenValido(String token, UserDetails userDetails);
    boolean isTokenExpirado(String token);
    Date extrairExpiracao(String token);
    <T> T extrairClaim(String token, Function<Claims, T> claimsResolver);
    Claims extrairTodosClaims(String token);
}

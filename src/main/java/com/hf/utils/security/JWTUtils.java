package com.hf.utils.security;

import io.jsonwebtoken.*;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JWTUtils {
    public static String generateJWT(Map<String, Object> map){
        System.out.println("f");
        String compact = Jwts.builder().setClaims(map)
                .setExpiration(new Date(System.currentTimeMillis() + 1 * 60 * 60 * 1000))
                .signWith(SignatureAlgorithm.HS512,"1234").compact();
        return compact;
    }

    public static Map parseJWTs(String token){
//可以同时获取到header信息
//        Jwt<Header, Claims> jwt = Jwts.parser().setSigningKey("123").parseClaimsJwt(token);
        Claims claims = Jwts.parser().setSigningKey("1234").parseClaimsJws(token).getBody();
        return claims;
    }
}

package com.hf.filter;

import com.hf.utils.security.JWTUtils;
import org.junit.jupiter.params.shadow.com.univocity.parsers.common.input.CharInput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.naming.BinaryRefAddr;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

public class AuthenFilter extends BasicAuthenticationFilter {
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenFilter.class);

    public AuthenFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        LOGGER.info("插入");
        String token=request.getHeader("token");
        String username=null;
        Collection<SimpleGrantedAuthority> authorities =null;
        try {
            Map map = JWTUtils.parseJWTs(token);
            username=(String) map.get("username");
            List authority = (List) map.get("authority");
            authorities=new HashSet<SimpleGrantedAuthority>();
            authorities.add(new SimpleGrantedAuthority((String) ((Map)authority.get(0)).get("authority")));
        } catch (Exception e) {

            System.out.println("dddddd");
        }
        System.out.println("1111");
        Authentication authentication;
        if(authorities==null){
            authentication  =new UsernamePasswordAuthenticationToken(username,null);
        }else {
            authentication =new UsernamePasswordAuthenticationToken(username,null,authorities);
        }
        String s=request.getServletPath();
        if (request.getServletPath().equals("/login")){
            GrantedAuthority grantedAuthority=new SimpleGrantedAuthority("admin");
            authorities=new HashSet<>();
            HashSet<SimpleGrantedAuthority> authorities1 =new HashSet<>();
            authorities1.add((SimpleGrantedAuthority) grantedAuthority);
            authorities=authorities1;
            authentication =new UsernamePasswordAuthenticationToken(username,null,authorities);
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request,response);
    }

}

package com.hf.controller;

import com.hf.utils.HttpResponse;
import com.hf.utils.RedisUtils;
import com.hf.utils.security.JWTUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.io.PipedReader;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

@RestController
public class Login {
    @Autowired
    RedisUtils redisUtils;
    private final static Logger LOGGER = LoggerFactory.getLogger(Login.class);


    @RequestMapping ("/login")
    public HttpResponse login(@RequestParam String username,@RequestParam String password){
        LOGGER.info("访问logina");
        Map<String,Object> map =new HashMap();
        map.put("username",username);
        Collection<SimpleGrantedAuthority> collection =new HashSet<>();
        SimpleGrantedAuthority simpleGrantedAuthorityA =new SimpleGrantedAuthority("admin");
        collection.add(simpleGrantedAuthorityA);
        map.put("authority",collection);

        String jwt = JWTUtils.generateJWT(map);
        return new HttpResponse(200,"成功",jwt);

    }
    @PreAuthorize("hasAuthority('admin')")
    @RequestMapping ("/a")
    public HttpResponse loginTest(){

//        redisUtils.setRedisData();
        Map<String,String> map =new HashMap<>();
        map.put("hf","01");
        redisUtils.setRedisHashData(map);
        HashMap<String,String> dk = (HashMap)redisUtils.getRedisHashData("dk", "01");

        String redisData = redisUtils.getRedisData();
        return new HttpResponse(200,"成功",dk);
    }


}

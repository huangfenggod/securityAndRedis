package com.hf.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.Duration;
import java.util.Map;
import java.util.concurrent.TimeUnit;
@Component
public class RedisUtils {
    @Autowired
    RedisTemplate redisTemplate;

     public void setRedisData(){
        
        redisTemplate.opsForValue().set("hf","01",10, TimeUnit.MINUTES);

    }
    public void setRedisHashData(Map map){
         redisTemplate.opsForHash().put("dk","01",map);
    }

    public Object getRedisHashData(String key,String hashKey){
         return redisTemplate.opsForHash().get(key,hashKey);
    }

    public String getRedisData(){
        String hf = (String)redisTemplate.opsForValue().get("hf");
        Long t =redisTemplate.getExpire("hf");
        return hf+t;
    }
}

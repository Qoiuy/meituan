package com.cinsc.meituan.service.redis;

import com.cinsc.meituan.enums.ResultEnum;
import com.cinsc.meituan.exception.MyException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class RedisService {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;//注入redis的模板

    @Autowired
    private RedisTemplate redisTemplate;

    public void setString(String key,String value){
        setObject(key,value);
    }

    public void setList(String key,List value){
        setObject(key,value);
    }



    public void setObject(String key,Object value){
        //redis有几种 String List set zset hash
        if (StringUtils.isEmpty(key)||value==null){
            throw new MyException(ResultEnum.PARAM_ERROR);
        }
        //判断类型 存放String类型
        if(value instanceof String){
            log.info("value:{}",value);
            String strValue = (String)value;
            log.info("strValue:{}",strValue);
            stringRedisTemplate.opsForValue().set(key,strValue,60*4, TimeUnit.SECONDS);//过期时间为4分钟
            return;
        }

        //存放List类型
        if(value instanceof List){
            List<String> listValue = (List<String>) value;
            redisTemplate.opsForList().leftPush(key,value);
        }
    }

    public String getKey(String key){
        return stringRedisTemplate.opsForValue().get(key);
    }

    public List<String> getList(String key){
        return (List<String>) redisTemplate.opsForList().leftPop(key);
    }

}

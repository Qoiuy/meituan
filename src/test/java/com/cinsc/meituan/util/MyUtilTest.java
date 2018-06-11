package com.cinsc.meituan.util;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class MyUtilTest {

    @Test
    public void getUniqueKey() {
        log.info("uniqueKey:{}",MyUtil.getUniqueKey());

    }


}
package com.cinsc.meituan.dao;

import com.cinsc.meituan.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void findByUserId() {
        User user = userRepository.findByUserId(1);
        log.info("user:{}",user);
        assertNotNull(user);
    }

    @Test
    public void findAll() {
        List<User> users = userRepository.findAll();
        log.info("users:{}",users.toArray());
        assertNotNull(users);
    }

    @Test
    public void findByUserCount() {
    }

    @Test
    public void findByTelephone() {
    }
}
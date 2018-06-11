package com.cinsc.meituan.dao;

import com.cinsc.meituan.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
    User findByUserId(Integer userId);
    User findByUserName(String username);
    User findByTelephone(String telephone);
    User deleteByUserName(String username);
    User findAllByEmailAddress(String emailAddress);
}

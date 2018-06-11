package com.cinsc.meituan.service;

import com.cinsc.meituan.entity.User;

public interface UserService {
    public Object addUser(User user);
    public User deleteUser(String username);
    public User queryUser(String username);
    public User getCurrentUser();
    public Object updateUser(User user);
}

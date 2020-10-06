package com.cb.travel.service;

import com.cb.travel.entity.User;
import org.springframework.stereotype.Service;

public interface UserService {
    void register(User user);
    User login(User user);
}

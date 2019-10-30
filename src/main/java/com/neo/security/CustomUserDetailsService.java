package com.neo.security;

import com.neo.model.CustomUser;
import com.neo.service.UserService;
import com.neo.service.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserServiceImpl userServiceImpl;
    private UserService userService;

    @Override
    public CustomUser loadUserByUsername(String userName) throws UsernameNotFoundException {
        CustomUser user = userServiceImpl.getCustomUserByUserName(userName);
        if (user == null) {
            throw new  UsernameNotFoundException("Can not find the User");
        }
        LOGGER.info("user name："+userName+" role："+user.getAuthorities().toString());
        return user;
    }
}

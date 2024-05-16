package com.spring.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private ApplicationUserDao applicationUserDao;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        return applicationUserDao.selectUserByUserName(userName)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User %s is Not found", userName)));
    }
}

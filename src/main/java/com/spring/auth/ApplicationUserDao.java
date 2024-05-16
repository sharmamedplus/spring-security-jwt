package com.spring.auth;

import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("mock")
public interface ApplicationUserDao {
    Optional<ApplicationUser> selectUserByUserName(String userName);
}

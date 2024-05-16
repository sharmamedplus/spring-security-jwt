package com.spring.auth;

import com.spring.security.ApplicationUserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class MockedApplicationUserDaoImpl implements ApplicationUserDao{

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public Optional<ApplicationUser> selectUserByUserName(String userName) {
        Optional<ApplicationUser> users = getAllUsers().stream()
                .filter(user -> user.getUsername().equalsIgnoreCase(userName))
                .findFirst();
        return users;
    }

    private List<ApplicationUser> getAllUsers(){

        ApplicationUser annasmith = new ApplicationUser(
                "annasmith",
                passwordEncoder.encode("password"),
                ApplicationUserRole.STUDENT.getGrantedAuthorities(),
                true, true, true, true);

        ApplicationUser linda = new ApplicationUser(
                "linda",
                passwordEncoder.encode("password"),
                ApplicationUserRole.ADMIN.getGrantedAuthorities(),
                true, true, true, true);

        ApplicationUser tom = new ApplicationUser(
                "tom",
                passwordEncoder.encode("password"),
                ApplicationUserRole.ADMIN_TRAINEE.getGrantedAuthorities(),
                true, true, true, true);

        return Arrays.asList(annasmith, linda, tom);
    }
}

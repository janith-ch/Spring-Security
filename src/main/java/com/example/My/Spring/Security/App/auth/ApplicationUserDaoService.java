package com.example.My.Spring.Security.App.auth;

import com.example.My.Spring.Security.App.security.ApplicationUserRoles;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("user_dao")
public class ApplicationUserDaoService implements ApplicationUserDao {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Optional<ApplicationUser> selectApplicationUserByUsername(String username) {
        return getApplicationUser().stream()
                .filter(applicationUser -> username.equals(applicationUser.getUsername()))
                .findFirst();
    }

    private List<ApplicationUser> getApplicationUser() {
        List<ApplicationUser> applicationUsers = Lists.newArrayList(
                new ApplicationUser(
                        "Janith",
                        passwordEncoder.encode("password"),
                        ApplicationUserRoles.ADMIN.getGrantedAuthorities(),
                        true,
                        true,
                        true,
                        true),
                new ApplicationUser(
                        "Jone",
                        passwordEncoder.encode("password"),
                        ApplicationUserRoles.STUDENT.getGrantedAuthorities(),
                        true,
                        true,
                        true,
                        true),
                new ApplicationUser(
                        "Morgan",
                        passwordEncoder.encode("password"),
                        ApplicationUserRoles.ADMINTRAINEE.getGrantedAuthorities(),
                        true,
                        true,
                        true,
                        true)


        );
        return applicationUsers;
    }
}

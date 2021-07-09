package com.example.security.auth;

import com.example.security.security.UserRoles;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("fake")
public class FakeApplicationUserRepositoryService implements ApplicationUserRepository {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public FakeApplicationUserRepositoryService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }


    private List<ApplicationUser> getApplicationUsers() {
        List<ApplicationUser> applicationUsers = Lists.newArrayList(
                new ApplicationUser(
                        "annasmith",
                        passwordEncoder.encode("password"),
                        UserRoles.STUDENT.getGrantedAuthority(),
                        true,
                        true,
                        true,
                        true
                ),
                new ApplicationUser(
                        "linda",
                        passwordEncoder.encode("password"),
                        UserRoles.ADMIN.getGrantedAuthority(),
                        true,
                        true,
                        true,
                        true
                ),
                new ApplicationUser(
                        "tom",
                        passwordEncoder.encode("password"),
                        UserRoles.ADMINTRAINEE.getGrantedAuthority(),
                        true,
                        true,
                        true,
                        true
                )
        );

        return applicationUsers;
    }

    @Override
    public Optional<ApplicationUser> getApplicationUSerByUsername(String username) {
        return getApplicationUsers()
                .stream()
                .filter(applicationUser -> username.equals(applicationUser.getUsername()))
                .findFirst();
    }
}

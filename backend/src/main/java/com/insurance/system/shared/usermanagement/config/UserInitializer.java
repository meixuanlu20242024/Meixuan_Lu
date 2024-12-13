package com.insurance.system.shared.usermanagement.config;

import com.insurance.system.shared.usermanagement.domain.User;
import com.insurance.system.shared.usermanagement.domain.UserType;
import com.insurance.system.shared.domain.repository.UserRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserInitializer implements InitializingBean {

    private final UserRepository userRepository;


    @Autowired
    public UserInitializer(UserRepository userRepository) {
        this.userRepository = userRepository;

    }

    @Override
    public void afterPropertiesSet() throws Exception {
        initializeAdminUser();
    }

    private void initializeAdminUser() {
        if (userRepository.count() == 0) {
            User admin = new User();
            admin.setFirstName("Admin");
            admin.setLastName("User");
            admin.setUsername("admin");
            admin.setEmail("admin@example.com");
//            admin.setPassword(passwordEncoder.encode("password123"));
            admin.setEnabled(true);
            admin.setAttempts(0);
//            admin.setRoles(Collections.singleton(new Role("ROLE_ADMIN")));
//            admin.setPrivileges(Collections.singleton(new Privilege("PRIVILEGE_ADMIN")));
            admin.setUsertype(UserType.ADMIN);
//            admin.setProvider(AuthProvider.LOCAL);

            userRepository.save(admin);
        }
    }
}

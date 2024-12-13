
//import repository.com.insurance.system.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//
//import java.util.Optional;
//
//@Component
//public class DataInitializer implements CommandLineRunner {
//
//    @Autowired
//    private UserRepository userRepository;
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    @Override
//    public void run(String... args) throws Exception {
//        // Check if the users already exist
//        if (userRepository.findByUsername("user1") == null) {
//            User user1 = User.builder()
//                    .username("user1")
//                    .password(passwordEncoder.encode("password123"))
//                    .build();
//            userRepository.save(user1);
//        }
//
//        if (userRepository.findByUsername("ethanm") == null) {
//            User ethanm = User.builder()
//                    .username("ethanm")
//                    .password(passwordEncoder.encode("password123"))
//                    .build();
//            userRepository.save(ethanm);
//        }
//    }
//}
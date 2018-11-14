package lostadnpet.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


import java.util.Arrays;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;
    @Autowired
    PetRepository messageRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... string )throws Exception {
        boolean dataLoader = true;
        if (dataLoader) {
            roleRepository.save(new Role("USER"));
            roleRepository.save(new Role("ADMIN"));

            Role adminRole = roleRepository.findByRole("ADMIN");
            Role userRole = roleRepository.findByRole("USER");


            User user = new User("@email.com", "password", "FirstUser", "LastName", true, "userone");
            user.setRoles(Arrays.asList(userRole));
            userRepository.save(user);

            user = new User("admin@gmail.com", "password", "AdminName", "LastName", true, "adminone");
            user.setRoles(Arrays.asList(adminRole));
            userRepository.save(user);

//            Pet pet = new Pet("boby","12-03-2018", "black withwhile strip", "lost", user.getFirstName());
//            messageRepository.save(pet);
        }
    }
}

package com.switchfully.digibooky;

import com.switchfully.digibooky.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class GreetingController {

    private UserRepository userRepository;

    public GreetingController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping("/hello")
    @ResponseBody
    String getWelcomeMessage() {
        System.out.println(userRepository.users);

        return "test";
    }
}

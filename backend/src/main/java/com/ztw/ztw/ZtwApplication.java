package com.ztw.ztw;

import com.ztw.ztw.model.User;
import com.ztw.ztw.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.security.Principal;
import java.util.List;
import java.util.Map;

@SpringBootApplication
@EnableSwagger2
@RestController
@Slf4j
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class ZtwApplication {

    public static Logger logger = LoggerFactory.getLogger(ZtwApplication.class);

    private final UserService userService;

    @GetMapping("/")
    public RedirectView home(Principal principal) {
        Map<String, Object> authDetails = (Map<String, Object>) ((OAuth2Authentication) principal).getUserAuthentication().getDetails();

        // Maybe add new user
        String email = (String) authDetails.get("email");
        List<String> emails = userService.getUsersEmails();
        if (!emails.contains(email)) {
            User newUser = new User();
            newUser.setName((String) authDetails.get("first_name"));
            newUser.setSurname((String) authDetails.get("last_name"));
            newUser.setEmail((String) authDetails.get("email"));
            newUser.setSex("M");
            newUser.setActivity("brak");
            userService.addUser(newUser);
        }

        logger.info("User logged: " + email);
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("http://localhost:3000/profile");
        //redirectView.setUrl("http://localhost:8080/ingredients");
        return redirectView;
    }

    public static void main(String[] args) {
        SpringApplication.run(ZtwApplication.class, args);
    }
}

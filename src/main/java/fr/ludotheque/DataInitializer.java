package fr.ludotheque;

import fr.ludotheque.model.Editor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class DataInitializer implements CommandLineRunner {



    @Override
    public void run(String... args) throws Exception {
        log.debug("initializing  data...");


        System.out.println(new BCryptPasswordEncoder().encode("myPassword"));

        Editor editor = new Editor();
        editor.setId(1);
    }
}

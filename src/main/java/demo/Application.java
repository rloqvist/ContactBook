package demo;

import demo.db.DBConnector;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *
 * @author rloqvist
 */
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        new DBConnector().create();
        SpringApplication.run(Application.class, args);
    }

}
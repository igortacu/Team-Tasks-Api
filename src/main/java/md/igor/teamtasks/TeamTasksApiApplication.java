package md.igor.teamtasks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//embedded web server (Tomcat)
@SpringBootApplication
public class TeamTasksApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(TeamTasksApiApplication.class);
    }
}

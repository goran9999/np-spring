package rs.fon.silab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "rs.fon.silab")
@EntityScan("rs.fon.silab.model")
@EnableJpaRepositories("rs.fon.silab.repository")
/**
 * 
 * @author goran
 * Entry point in spring boot application
 */
public class SpringCoursesApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCoursesApplication.class, args);
	}

}
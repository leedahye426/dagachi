package kitri.dagachi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;


//@EnableJpaAuditing
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class DagachiApplication {

	public static void main(String[] args) {
		SpringApplication.run(DagachiApplication.class, args);
	}

}

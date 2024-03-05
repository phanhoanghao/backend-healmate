package bluedragonvn.com.healmate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class HealmateApplication {
	public static void main(String[] args) {
		SpringApplication.run(HealmateApplication.class, args);
	}

}

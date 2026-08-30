package school.sptech.sistema_alertas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SistemaAlertasApplication {

	public static void main(String[] args) {
		SpringApplication.run(SistemaAlertasApplication.class, args);
	}

}

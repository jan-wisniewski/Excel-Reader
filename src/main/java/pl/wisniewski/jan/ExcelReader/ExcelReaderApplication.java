package pl.wisniewski.jan.ExcelReader;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"pl.wisniewski.jan.ExcelReader"})
@EnableJpaRepositories
public class ExcelReaderApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExcelReaderApplication.class, args);
	}

}

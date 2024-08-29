package com.example.leontis;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Properties;

@SpringBootApplication
public class LeontisApplication {

	public static void main(String[] args) {

		// Carregar variáveis de ambiente do arquivo .env
		Dotenv dotenv = Dotenv.load();

		// Configurar propriedades padrão a partir das variáveis do .env
		Properties properties = new Properties();
		properties.put("DATABASE_URL", dotenv.get("DATABASE_URL"));
		properties.put("DATABASE_USER", dotenv.get("DATABASE_USER"));
		properties.put("DATABASE_PASSWORD", dotenv.get("DATABASE_PASSWORD"));

		// Inicializar a aplicação Spring Boot com as propriedades padrão
		SpringApplication app = new SpringApplication(LeontisApplication.class);
		app.setDefaultProperties(properties);
		app.run(args);
	}

}

package com.fatec;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ulisesbocchio.jasyptspringboot.annotation.EncryptablePropertySource;

@SpringBootApplication
@EncryptablePropertySource(name = "EncryptedPropertiesDev", value = "classpath:application-dev.properties")
public class ProjetoSistemasInformacoesApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjetoSistemasInformacoesApplication.class, args);
	}

}

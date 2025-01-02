package ru.avagimov.isandsProject;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ru.avagimov.isandsProject.models.*;
import ru.avagimov.isandsProject.services.ModelService;
import ru.avagimov.isandsProject.types.ApplianceType;

import java.util.Map;

@SpringBootApplication
public class IsandsProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(IsandsProjectApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

}

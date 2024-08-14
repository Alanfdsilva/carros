package com.unitau.carros.carros.validator;

import java.util.Optional;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.unitau.carros.carros.anotations.CarroFK;
import com.unitau.carros.carros.model.Carro;
import com.unitau.carros.carros.repository.CarroRepository;

public class CarroFKValidator implements ConstraintValidator<CarroFK, Long> {
	@Autowired
	private CarroRepository repository;

	@Override
	public boolean isValid(Long value, ConstraintValidatorContext context) {
		if (value != null) {
			Optional<Carro> search = repository.findById(value);
			if (search.isPresent()) {
				return true;
			}
		}
		return false;
	}
}
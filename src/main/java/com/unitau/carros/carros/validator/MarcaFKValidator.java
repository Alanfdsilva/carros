package com.unitau.carros.carros.validator;

import java.util.Optional;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.unitau.carros.carros.anotations.MarcaFK;
import com.unitau.carros.carros.model.Marca;
import com.unitau.carros.carros.repository.MarcaRepository;

public class MarcaFKValidator implements ConstraintValidator<MarcaFK, Long> {
	@Autowired
	private MarcaRepository repository;

	@Override
	public boolean isValid(Long value, ConstraintValidatorContext context) {
		if (value != null) {
			Optional<Marca> search = repository.findById(value);
			if (search.isPresent()) {
				return true;
			}
		}
		return false;
	}
}
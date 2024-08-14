package com.unitau.carros.carros.validator;

import java.util.Optional;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.unitau.carros.carros.anotations.PessoaFK;
import com.unitau.carros.carros.model.Pessoa;
import com.unitau.carros.carros.repository.PessoaRepository;

public class PessoaFKValidator implements ConstraintValidator<PessoaFK, Long> {
	@Autowired
	private PessoaRepository repository;

	@Override
	public boolean isValid(Long value, ConstraintValidatorContext context) {
		if (value != null) {
			Optional<Pessoa> search = repository.findById(value);
			if (search.isPresent()) {
				return true;
			}
		}
		return false;
	}
}
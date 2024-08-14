package com.unitau.carros.carros.anotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.unitau.carros.carros.validator.CarroFKValidator;

@Constraint(validatedBy = CarroFKValidator.class)
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CarroFK {
    String message() default "Carro invalido";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default { };
}
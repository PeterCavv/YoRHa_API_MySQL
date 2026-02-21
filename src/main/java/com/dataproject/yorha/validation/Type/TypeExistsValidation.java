package com.dataproject.yorha.validation.Type;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

@Component
public class TypeExistsValidation implements ConstraintValidator<TypeExists, String> {

    private final TypeRepository typeRepository;

    public TypeExistsValidation(TypeRepository typeRepository) {
        this.typeRepository = typeRepository;
    }

    @Override
    public boolean isValid(String id, ConstraintValidatorContext context) {
        if (id == null || id.isEmpty()) {
            return false;
        }

        return typeRepository.existsById(id);
    }
}
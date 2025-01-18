package com.namyxc.locations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CoordinateValidator implements ConstraintValidator<Coordinate, Double> {

    private CoordinateType coordinateType;


    @Override
    public void initialize(Coordinate constraintAnnotation) {
        coordinateType = constraintAnnotation.type();
    }

    @Override
    public boolean isValid(Double v, ConstraintValidatorContext constraintValidatorContext) {
        double max = coordinateType == CoordinateType.LAT ? 90 : 180;
        double min = coordinateType == CoordinateType.LAT ? -90 : -180;
        return v >= min && v <= max;
    }
}

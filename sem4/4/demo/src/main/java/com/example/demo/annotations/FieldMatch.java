package com.example.demo.annotations;

import com.example.demo.validators.FieldMatchValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Target({TYPE, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = FieldMatchValidator.class)
public @interface FieldMatch {
    String message() default "Fields don't match";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    String first();
    String second();

    @Target({TYPE, ANNOTATION_TYPE})
    @Retention(RUNTIME)
    @interface List {
        FieldMatch[] value();
    }
}

package com.example.demo.models;

import com.example.demo.annotations.FieldMatch;
import jakarta.validation.constraints.*;
import lombok.Data;

@FieldMatch.List({
        @FieldMatch(first = "password", second = "confirmPassword",
                message = "The password fields must be match")
})
@Data
public class User {

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "Password is required")
    @Pattern(regexp = ("^(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*]).{3,}$"),
            message = "Password should contain at least one digit, one uppercase letter, one special character")
    private String password;

    @NotBlank(message = "Please confirm your password")
    private String confirmPassword;

    @AssertTrue(message = "You must agree with the terms")
    private boolean terms;

    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^\\+7-\\d{3}-\\d{3}-\\d{2}-\\d{2}$",
            message = "Phone number must match the pattern +7-XXX-XXX-XX-XX")
    private String phoneNumber;

    @Min(value = 18, message = "Age must be at least 18")
    @Max(value = 100, message = "Age must be less than or equal to 100")
    private int age;
}

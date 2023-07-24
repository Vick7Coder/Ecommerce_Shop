package com.vick7.library.dto;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data @NoArgsConstructor @AllArgsConstructor
public class AdminDto {
    @Size(min = 2, max = 10, message = "Invalid first name! (2 to 10 characters required)")
    private String firstName;
    @Size(min = 2, max = 10, message = "Invalid last name! (2 to 10 characters required)")
    private String lastName;
    private String username;
    @Size(min = 5, max = 15, message = "Invalid password name! (5 to 15 characters required)")
    private String password;
    private String repeatPassword;
}

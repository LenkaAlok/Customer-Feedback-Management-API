package com.crudoperation.Crudoperation.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FeedbackRequest {

    @NotBlank(message = "Feedback message is required")
    @Size(max = 2000, message = "Feedback cannot exceed 2000 characters")
    private String message;
}


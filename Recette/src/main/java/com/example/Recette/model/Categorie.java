package com.example.Recette.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Categorie {
    private Long id;
    @NotNull
    @NotBlank
    private String nom;
    @NotNull
    @NotBlank
    private String description;
}

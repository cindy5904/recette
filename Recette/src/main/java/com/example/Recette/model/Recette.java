package com.example.Recette.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Recette {
    private Long id;
    @NotNull
    @NotBlank
    private String nom;
    @NotNull
    private List<String> ingredients = new ArrayList<>();
    @NotNull
    @NotBlank
    private String instructions;
    @NotNull
    private int idCategorie = 0;
}

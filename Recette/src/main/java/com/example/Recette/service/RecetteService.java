package com.example.Recette.service;

import com.example.Recette.model.Recette;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class RecetteService implements RecetteI<Recette>{
    private List<Recette> recettes = new ArrayList<>();
    private Long currentId = 1L;


    @Override
    public Recette create(Recette recette) {
        recette.setId(currentId++);
        recettes.add(recette);
        return recette;
    }

    @Override
    public Recette getById(Long id) {
        return recettes.stream().filter(recette-> recette.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public List<Recette> getAll() {
        return recettes;
    }

    @Override
    public Recette update(Long id, Recette updateRecette) {
        Recette recetteExisting = getById(id);
        if (recetteExisting != null) {
            recetteExisting.setNom(updateRecette.getNom());
            recetteExisting.setIngredients(updateRecette.getIngredients());
            recetteExisting.setInstructions(updateRecette.getInstructions());
            recetteExisting.setIdCategorie(updateRecette.getIdCategorie());
        }
        return recetteExisting;
    }

    @Override
    public void delete(Long id) {
        recettes.removeIf(recette -> recette.getId().equals(id));
    }
}

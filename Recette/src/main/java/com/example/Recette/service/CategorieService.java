package com.example.Recette.service;

import com.example.Recette.model.Categorie;
import com.example.Recette.model.Recette;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategorieService implements RecetteI<Categorie> {
    private List<Categorie> categories = new ArrayList<>();

    private Long currentId = 1L;

    public CategorieService() {
        categories.add(new Categorie(1L, "Entrées", "Découvrez nos délicieuses entrées pour ouvrir votre appétit."));
        categories.add(new Categorie(2L, "Plats", "Explorez nos plats principaux savoureux pour tous les goûts."));
        categories.add(new Categorie(3L, "Desserts", "Faites-vous plaisir avec nos desserts gourmands et raffinés."));
    }

    @Override
    public Categorie create(Categorie categorie) {
        categorie.setId(currentId++);
        categories.add(categorie);
        return categorie;
    }


    @Override
    public Categorie getById(Long id) {
        return categories.stream().filter(categorie-> categorie.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public List<Categorie> getAll() {
        return categories;
    }

    @Override
    public Categorie update(Long id, Categorie updateCategorie) {
        Categorie categorieExisting = getById(id);
        if (categorieExisting != null) {
            categorieExisting.setNom(updateCategorie.getNom());
            categorieExisting.setDescription(updateCategorie.getDescription());

        }
        return categorieExisting;
    }

    @Override
    public void delete(Long id) {
        categories.removeIf(categorie -> categorie.getId().equals(id));
    }
}

package com.example.Recette.controller;

import com.example.Recette.model.Categorie;
import com.example.Recette.model.Recette;
import com.example.Recette.service.CategorieService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CategorieController {
    private CategorieService categorieService;

    public CategorieController(CategorieService categorieService) {
        this.categorieService = categorieService;
    }

    @GetMapping("/formulaireCategory")
    public String formAddCategorie(Model model) {
        model.addAttribute("categorie", new Categorie());

        return "formCategory";
    }

    @PostMapping("/formulaireCategory")
    public String addCategory(@Valid @ModelAttribute("categorie") Categorie categorie, BindingResult result) {
        if(result.hasErrors()){
            return "formCategory";
        } else {
            if (categorie.getId() != null) {
                categorieService.update(categorie.getId(), categorie);
            } else {
                categorieService.create(categorie);
            }
        }
        return "redirect:/categories";
    }

    @GetMapping("/categories")
    public String getAllCategories(Categorie categorie, Model model) {
        List<Categorie> categories = categorieService.getAll();
        model.addAttribute("categories", categories);
        return "categories";
    }

    @GetMapping("/categorie/{id}")
    public String getCategorieById(@PathVariable("id") Long id, Model model){
        model.addAttribute("categorie", categorieService.getById(id));
        return "detailCategorie";
    }

    @GetMapping("/deleteCat")
    public String delete(@RequestParam("categorieId") Long id) {
        categorieService.delete(id);
        return "redirect:/categories";
    }

    @GetMapping("/updateCat")
    public String formUpdateCategorie(@RequestParam("categorieId") Long id, Model model){
        Categorie categorie = categorieService.getById(id);
        model.addAttribute("categorie", categorie);
        return "formCategory";
    }
}

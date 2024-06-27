package com.example.Recette.controller;

import com.example.Recette.model.Categorie;
import com.example.Recette.model.Recette;
import com.example.Recette.service.CategorieService;
import com.example.Recette.service.RecetteService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Controller
public class RecetteController {
    @Autowired
    private RecetteService recetteService;
    @Autowired
    private CategorieService categorieService;
    private List<String> ingredients;


    public RecetteController(RecetteService recetteService) {
        this.recetteService = recetteService;
        this.categorieService = categorieService;
    }

    @GetMapping("/")
    public String home(){
        return "home";
    }

    @GetMapping("/formulaire")
    public String formAddRecette(Model model) {
        model.addAttribute("recette", new Recette());
        model.addAttribute("ingredients", ingredients);
        model.addAttribute("categories", categorieService.getAll());
        return "formRecette";
    }

    @PostMapping("/formulaire")
    public String addRecette(@Valid @ModelAttribute("recette") Recette recette, BindingResult result, @RequestParam("ingredient") String ingredient) {
        if(result.hasErrors()){
            return "formRecette";
        } else {
            if (ingredient != null && !ingredient.isEmpty()) {
                recette.getIngredients().add(ingredient);
            }
            if (recette.getId() != null) {
                recetteService.update(recette.getId(), recette);
            } else {
                recetteService.create(recette);
            }
        }
        return "redirect:/recettes";
    }

    @GetMapping("/recettes")
    public String getAllRecettes(Recette recette, Model model) {
        List<Recette> recettes = recetteService.getAll();
        model.addAttribute("recettes", recettes);
        return "recettes";
    }

    @GetMapping("/recette/{id}")
    public String getRecetteById(@PathVariable("id") Long id, Model model){
        model.addAttribute("recette", recetteService.getById(id));
        return "detailRecette";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("recetteId") Long id) {
        recetteService.delete(id);
        return "redirect:/recettes";
    }

    @GetMapping("/update")
    public String formUpdateRecette(@RequestParam("recetteId") Long id, Model model){
        Recette recette = recetteService.getById(id);
        model.addAttribute("recette", recette);
        return "formRecette";
    }
}

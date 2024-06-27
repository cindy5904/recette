package com.example.Recette.service;

import com.example.Recette.model.Recette;

import java.util.List;

public interface RecetteI<T> {
    T create(T entity);
    T getById(Long id);
    List<T> getAll();
    T update(Long id, T entity);
    void delete(Long id);


}

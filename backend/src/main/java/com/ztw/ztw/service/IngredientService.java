package com.ztw.ztw.service;

import com.ztw.ztw.model.Ingredient;
import com.ztw.ztw.repository.IngredientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class IngredientService {

    private final IngredientRepository ingredientRepository;

    // CREATE
    public Ingredient addIngredient(Ingredient ingredient) {
        return ingredientRepository.save(ingredient);
    }

    // READ
    public List<Ingredient> getIngredients() {
        return ingredientRepository.findAll();
    }

    public Ingredient getSingleIngredient(Integer id) {
        Optional<Ingredient> ingredient = ingredientRepository.findById(id);
        return ingredient.orElse(null);
    }

    // UPDATE
    @Transactional
    public Ingredient editIngredient(Ingredient ingredient) {
        Optional<Ingredient> ingredientEditedOptional = ingredientRepository.findById(ingredient.getIngredientId());

        if(ingredientEditedOptional.isPresent()){
            Ingredient ingredientEdited = ingredientEditedOptional.get();
            ingredientEdited.setName(ingredient.getName());
            ingredientEdited.setMeasureUnit(ingredient.getMeasureUnit());
            ingredientEdited.setUnits(ingredient.getUnits());
            ingredientEdited.setKcalPer100g(ingredient.getKcalPer100g());
            ingredientEdited.setProteinPer100g(ingredient.getProteinPer100g());
            ingredientEdited.setFatsPer100g(ingredient.getFatsPer100g());
            ingredientEdited.setCarbohydratesPer100g(ingredient.getCarbohydratesPer100g());
            return ingredientEdited;
        }else{
            return null;
        }
    }

    // DELETE
    public boolean deleteIngredient(Integer id) {
        Optional<Ingredient> deletedIngredient = ingredientRepository.findById(id);
        if (deletedIngredient.isPresent()) {
            ingredientRepository.delete(deletedIngredient.get());
            return true;
        } else {
            return false;
        }
    }
}

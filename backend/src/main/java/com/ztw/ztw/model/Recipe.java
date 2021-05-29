package com.ztw.ztw.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer recipeId;
    private String name;
    private String instruction;
    private String image;
    private Double sumKcal;
    private Double sumProtein;
    private Double sumFats;
    private Double sumCarbohydrates;

    @ManyToMany
    @JoinTable(
            name = "ingredient_recipes",
            joinColumns = @JoinColumn(name = "recipeId"),
            inverseJoinColumns = @JoinColumn(name = "ingredientId"))
    Set<Ingredient> ingredients;
}

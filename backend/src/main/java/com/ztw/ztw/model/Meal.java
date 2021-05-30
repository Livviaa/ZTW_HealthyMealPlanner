package com.ztw.ztw.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "meal")
public class Meal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer mealId;
    private Integer dailyMenuId;
    private Double sumKcal;
    private Double sumProtein;
    private Double sumFats;
    private Double sumCarbohydrates;

    @ManyToMany
    @JoinTable(
            name = "recipe_meals",
            joinColumns = @JoinColumn(name = "mealId"),
            inverseJoinColumns = @JoinColumn(name = "recipeId"))
    Set<Recipe> recipes;
}

package com.ztw.ztw.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "ingredient")
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ingredientId;
    private String name;
    private String measureUnit;
    private Integer units;
    private Double kcalPer100g;
    private Double proteinPer100g;
    private Double fatsPer100g;
    private Double carbohydratesPer100g;
}

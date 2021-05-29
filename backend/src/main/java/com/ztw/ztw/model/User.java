package com.ztw.ztw.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    private String name;
    private String surname;
    private String sex;
    private Double weight;
    private Integer height;
    private String activity;
    private String email;
    private Date birthDate;
    private Double recommendedDailyKcal;
    private Double recommendedDailyProtein;
    private Double recommendedDailyFats;
    private Double recommendedDailyCarbohydrates;

    @OneToMany(cascade = CascadeType.REMOVE)
    @JoinColumn(name="userId", updatable = false, insertable = false)
    private List<DailyMenu> menus;
}

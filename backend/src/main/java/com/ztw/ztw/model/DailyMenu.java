package com.ztw.ztw.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "daily_menu")
public class DailyMenu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer dailyMenuId;
    private Integer userId;
    private Date date;
    private Double sumKcal;
    private Double sumProtein;
    private Double sumFats;
    private Double sumCarbohydrates;

    @OneToMany(cascade = CascadeType.REMOVE)
    @JoinColumn(name="dailyMenuId", updatable = false, insertable = false)
    private List<Meal> meals;
}

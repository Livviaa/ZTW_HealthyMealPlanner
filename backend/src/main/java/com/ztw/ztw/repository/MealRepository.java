package com.ztw.ztw.repository;

import com.ztw.ztw.model.Meal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MealRepository extends JpaRepository<Meal, Integer> {

    List<Meal> findAllByDailyMenuIdIn(List<Integer> ids);
}

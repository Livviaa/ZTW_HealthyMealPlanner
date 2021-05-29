package com.ztw.ztw.repository;

import com.ztw.ztw.model.DailyMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DailyMenuRepository extends JpaRepository<DailyMenu, Integer> {
}

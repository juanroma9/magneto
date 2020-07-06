package com.mercadolibre.magneto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

import com.mercadolibre.magneto.models.PeopleStatistic;

public interface PeopleStaticeRepository  extends JpaRepository<PeopleStatistic, Long>{
    
    @Query("SELECT (SELECT COUNT(p.isMutant) FROM PeopleStatistic p WHERE p.isMutant = 1 ) AS Mutant, "+
                  "COUNT(p.isMutant) AS Human FROM PeopleStatistic p WHERE p.isMutant = 0 ")
    List<Object[]> findStats();
}
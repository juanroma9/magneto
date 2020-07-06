package com.mercadolibre.magneto.services;

import java.util.List;

import com.mercadolibre.magneto.models.PeopleStatistic;
import com.mercadolibre.magneto.repositories.PeopleStaticeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("PeopleStatisticService")
public class PeopleStatisticService {
    
    @Autowired
    private PeopleStaticeRepository peopleStaticeRepository;

    public PeopleStatistic save(final PeopleStatistic peopleStatistic) {
        return peopleStaticeRepository.save(peopleStatistic);
    }

    public List<Object[]> findStats(){
        return peopleStaticeRepository.findStats();
    }

    public List<PeopleStatistic> all(){
        return peopleStaticeRepository.findAll();
    }
} 
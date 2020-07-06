package com.mercadolibre.magneto.models;

import javax.persistence.*;

import lombok.Data;

@Data
@Entity
@Table (name ="people_statistics")
public class PeopleStatistic {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "peopleStatistic_id")
    private long id;

    @Column(name = "adnMutant")
    private String adnMutant;

    @Column(name = "isMutant")
    private boolean isMutant;
}
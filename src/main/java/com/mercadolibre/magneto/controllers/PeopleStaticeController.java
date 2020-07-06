package com.mercadolibre.magneto.controllers;

import java.util.List;

import com.mercadolibre.magneto.DTO.DnaDTO;
import com.mercadolibre.magneto.models.CheckADN;
import com.mercadolibre.magneto.models.PeopleStatistic;
import com.mercadolibre.magneto.services.PeopleStatisticService;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@CrossOrigin
@RequestMapping("/")
public class PeopleStaticeController {

    @Autowired
    private PeopleStatisticService peopleStatisticService;
    
    private CheckADN checkADN;

	@GetMapping(value = "status")
    public String checkStatus(){
        return "ok";
    }
	
    @PostMapping(path = "mutant" , consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> add(@RequestBody DnaDTO dnaDto) {
        
        try {
            checkADN = new CheckADN();
            Boolean find = checkADN.isMutant(dnaDto.dna);  
            peopleStatisticService.save(checkADN.peopleStatistic);          
            
            if(find)
                return ResponseEntity.ok("Mutante");

        } catch (Exception e) {
            System.out.println("se reventó por esto ->"+ e);
        }

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Humano");
    }

    @GetMapping("stats")
    public ResponseEntity<?> findStats(){
        
        List<Object[]> peopleStatistics =  peopleStatisticService.findStats();
        float mutant = Float.parseFloat(peopleStatistics.get(0)[0].toString());
        float human  = Float.parseFloat(peopleStatistics.get(0)[1].toString());
        float ratio  = mutant/ (human + mutant);

        return ResponseEntity.ok("{\"ADN\":{\"count_mutant_dna\":"+mutant+", \"count_human_dna\":"+human+", \"ratio\":"+ratio+"} }");
    }

    @GetMapping("all")
    public List<PeopleStatistic> all(){
        return peopleStatisticService.all();
    }
    
}
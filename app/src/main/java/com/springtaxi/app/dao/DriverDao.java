package com.springtaxi.app.dao;

import com.springtaxi.app.dto.DriverAnalytics;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


@Repository
public class DriverDao {
    @PersistenceContext
    private EntityManager em;

    public DriverAnalytics getDriverAnalytics() {
        return DriverAnalytics.builder()
                .TauxOccupation(calculateOccupationRate())
//                .plagesHoraires()
//                .repartitionStatuts()
                .build();
    }

    private double calculateOccupationRate(){
        String query = "SELECT AVG (CASE WHEN statut ='EN_COURSE' THEN 1 ELSE 0 END) * 100 FROM Driver ";
        Object result = em.createQuery(query).getSingleResult();
        if(result != null){
            return (double) result;
        }else {
            return 0;
        }
    }

    }





package com.springtaxi.app.dao;

import com.springtaxi.app.dto.DriverAnalytics;
import com.springtaxi.app.entity.enums.DriverStatut;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Repository
public class DriverDao {
    @PersistenceContext
    private EntityManager em;

    public DriverAnalytics getDriverAnalytics() {
        return DriverAnalytics.builder()
                .TauxOccupation(calculateOccupationRate())
                .plagesHoraires(analyzeAvailabilitySlots())
                .repartitionStatuts(getStatusDistribution())
                .build();
    }

    private double calculateOccupationRate(){
        String query = "SELECT AVG (CASE WHEN statut ='IN_PROGRESS' THEN 1 ELSE 0 END) * 100 FROM Driver ";
        Object result = em.createQuery(query).getSingleResult();
        if(result != null){
            return (double) result;
        }else {
            return 0;
        }
    }

    private Map<String, Integer> analyzeAvailabilitySlots() {
        String jpql = "SELECT d.disponibiliteDebut, d.disponibiliteFin FROM Driver d";
        List<Object[]> results = em.createQuery(jpql).getResultList();

        Map<String, Integer> timeSlots = new HashMap<>();

        for (Object[] result : results) {
            LocalDateTime start = (LocalDateTime) result[0];
            LocalDateTime end = (LocalDateTime) result[1];

            // Format: "2024-01-01 08:00 - 15:00"
            String slot = String.format("%s %02d:00 - %02d:00",
                    start.toLocalDate(),
                    start.getHour(),
                    end.getHour());

            timeSlots.merge(slot, 1, Integer::sum);
        }

        return timeSlots;
    }

    private Map<DriverStatut, Integer> getStatusDistribution() {
        String jpql = "SELECT d.statut, COUNT(d) FROM Driver d GROUP BY d.statut";
        return em.createQuery(jpql, Object[].class)
                .getResultList()
                .stream()
                .collect(Collectors.toMap(
                        result -> (DriverStatut) result[0],
                        result -> ((Long) result[1]).intValue()
                ));
    }




}





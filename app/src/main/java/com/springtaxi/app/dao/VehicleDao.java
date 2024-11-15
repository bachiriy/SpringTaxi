package com.springtaxi.app.dao;
import com.springtaxi.app.entity.VehicleStatus;
import com.springtaxi.app.entity.VehicleType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Repository
public class VehicleDao {

    @Autowired
    private EntityManager entityManager;

    /*
     *Calculer le kilométrage moyen par type de véhicule
     */

    public Map<String, Double> calculateAverageMileageByType() {
        String jpql = "SELECT v.type, AVG(v.mileage) FROM Vehicle v GROUP BY v.type";
        Query query = entityManager.createQuery(jpql);
        List<Object[]> resultList = query.getResultList();

        Map<String, Double> result = new HashMap<>();
        for (Object[] resultRow : resultList) {
            VehicleType type = (VehicleType) resultRow[0];
            Double avgMileage = (Double) resultRow[1];
            result.put(type.name(), avgMileage);
        }
        return result;
    }


    /*
     * Calculer le taux d'utilisation par type de véhicule (pourcentage du temps où les véhicules sont EN_COURSE)
     */
    public Map<String, Double> calculateUsageRateByType() {
        String jpql = "SELECT v.type, SUM(CASE WHEN v.status = :status THEN 1 ELSE 0 END) / COUNT(v) * 100 FROM Vehicle v GROUP BY v.type";
        Query query = entityManager.createQuery(jpql);
        query.setParameter("status", VehicleStatus.IN_SERVICE); // Assuming "EN_COURSE" corresponds to "IN_SERVICE" in your enum

        List<Object[]> resultList = query.getResultList();
        Map<String, Double> result = new HashMap<>();

        for (Object[] resultRow : resultList) {
            VehicleType type = (VehicleType) resultRow[0];
            // Ensure that we safely cast the result to a Double
            Double usageRate = ((Number) resultRow[1]).doubleValue();  // This ensures the value is treated as a Double
            result.put(type.name(), usageRate);
        }

        return result;
    }


    /*
     *Calculer l'état de la flotte (nombre de véhicules par statut)
     *
     */

    public Map<String, Long> calculateFleetStatus() {
        String jpql = "SELECT v.status, COUNT(v) FROM Vehicle v GROUP BY v.status";
        Query query = entityManager.createQuery(jpql);
        List<Object[]> resultList = query.getResultList();

        Map<String, Long> result = new HashMap<>();
        for (Object[] resultRow : resultList) {
            VehicleStatus status = (VehicleStatus) resultRow[0];
            Long count = (Long) resultRow[1];
            result.put(status.name(), count);
        }
        return result;
    }



}

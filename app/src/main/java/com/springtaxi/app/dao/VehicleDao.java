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

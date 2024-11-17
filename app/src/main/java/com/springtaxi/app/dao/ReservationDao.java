package com.springtaxi.app.dao;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.springtaxi.app.entity.Reservation;


@Component
public class ReservationDao {

    // @Autowired
    // private EntityManager entityManager;

    // @Transactional
    // public Object getAvgPricePerKm(){
    //     String jpql = "SELECT AVG(r.price) FROM Reservation r";
    //     TypedQuery<Double> query = entityManager.createQuery(jpql, Double.class);
    //     // Query query = entityManager.createQuery(jpql);
    //     return query.getResultList();
    // }



    public HashMap<String, Object> getAllAnalytics(List<Reservation> reservations){
        Double avgPricePrKm = reservations.stream().mapToDouble(r -> r.getPrice() / r.getDistanceKm()).sum() / reservations.size();
        Double avgDistance = reservations.stream().mapToDouble(r -> r.getDistanceKm()).sum() / reservations.size();

        return new HashMap<String, Object>(){{
            put("average_price_per_km", avgPricePrKm);
            put("average_distance", avgDistance);
        }};
    }
}

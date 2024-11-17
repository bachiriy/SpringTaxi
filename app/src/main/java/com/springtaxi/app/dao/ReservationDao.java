package com.springtaxi.app.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class ReservationDao {

    @Autowired
    private EntityManager entityManager;

    @Transactional
    public Object getAvgPricePerKm(){
        String jpql = "SELECT AVG(r.price) FROM Reservation r";
        TypedQuery<Double> query = entityManager.createQuery(jpql, Double.class);
        // Query query = entityManager.createQuery(jpql);
        return query.getResultList();
    }
}

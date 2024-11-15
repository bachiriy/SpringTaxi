package com.springtaxi.app.repository;

import com.springtaxi.app.entity.Driver;
import com.springtaxi.app.entity.enums.DriverStatut;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface  DriverRepository extends JpaRepository <Driver, Integer> {

    List<Driver> findByStatut(DriverStatut statut);
    @Query("SELECT d FROM Driver d " +
            "WHERE d.disponibiliteDebut >= :startOfDay " +
            "AND d.disponibiliteDebut < :endOfDay " +
            "AND d.statut = 'AVAILABLE' " +
            "ORDER BY d.disponibiliteDebut ASC")
    Page<Driver> findAvailableDriversByDate(
            @Param("startOfDay") LocalDateTime startOfDay,
            @Param("endOfDay") LocalDateTime endOfDay,
            Pageable pageable
    );


}

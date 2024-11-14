package com.springtaxi.app.repository;

import com.springtaxi.app.entity.Driver;
import com.springtaxi.app.entity.enums.DriverStatut;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Repository
public interface  DriverRepository extends JpaRepository <Driver, Integer> {

     List<Driver> findByStatut(DriverStatut statut);

}

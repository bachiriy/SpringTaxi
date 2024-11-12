package com.springtaxi.app.repository;

import com.springtaxi.app.entity.Driver;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DriverRepository extends JpaRepository <Driver, Integer> {

}

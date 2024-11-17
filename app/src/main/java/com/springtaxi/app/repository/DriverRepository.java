package com.springtaxi.app.repository;

import com.springtaxi.app.entity.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

@Repository
public interface  DriverRepository extends JpaRepository <Driver, Integer> {

}

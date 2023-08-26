package com.powerledger.powerplant.repository;

import com.powerledger.powerplant.model.Battery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BatteryRepository extends JpaRepository<Battery, Integer> {
    List<Battery> findAllByPostcodeIsBetweenOrderByNameAsc(String lowerRange, String upperRange);
}

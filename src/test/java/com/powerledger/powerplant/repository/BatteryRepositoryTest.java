package com.powerledger.powerplant.repository;

import com.powerledger.powerplant.model.Battery;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

//@ExtendWith(SpringExtension.class)
//@Transactional
//@SpringBootTest(classes = PowerPlantApplication.class)
@DataJpaTest
class BatteryRepositoryTest {
    @Autowired
    private BatteryRepository batteryRepository;

    @AfterEach
    void tearDown() {
        batteryRepository.deleteAll();
    }
    @Test
    void findAllByPostcodeIsBetweenOrderByNameAsc() {
        List<Battery> batteries = new ArrayList<>();
        batteries.add(new Battery("Cannington", "6107", 13500));
        batteries.add(new Battery("Midland", "6057", 50500));
        batteryRepository.saveAll(batteries);

        List<Battery> batteryList = batteryRepository.findAllByPostcodeIsBetweenOrderByNameAsc("6000", "7000");
        assertEquals(batteryList.get(0).getName(), "Cannington");
    }
}
package com.powerledger.powerplant.serviceImpl;

import com.powerledger.powerplant.dto.BatteryDTO;
import com.powerledger.powerplant.repository.BatteryRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class BatteryServiceImplTest {
    @Mock
    private BatteryRepository batteryRepository;
    //private AutoCloseable autoCloseable;
    private BatteryServiceImpl batteryService;

    @BeforeEach
    void setUp() {
        //autoCloseable = MockitoAnnotations.openMocks(this);
        batteryService = new BatteryServiceImpl(batteryRepository);
    }
    @AfterEach
    void tearDown() throws Exception {
        //AutoClose can be replaced with @ExtendWith
        //autoCloseable.close();
    }

    @Test
    //@Disabled not to run this testcase
    void createBatteries() {
        List<BatteryDTO> batteryDTOS = new ArrayList<>();
        batteryDTOS.add(new BatteryDTO("Cannington", "6107", 13500));
        batteryDTOS.add(new BatteryDTO("Midland", "6057", 50500));
        batteryService.createBatteries(batteryDTOS);


        ArgumentCaptor<List<BatteryDTO>> listBatteryDTOArgumentCaptor = ArgumentCaptor.forClass(List.class);
        List<BatteryDTO> capturedBatteries = listBatteryDTOArgumentCaptor.capture();
        //Mockito.verify(batteryRepository).saveAll(listBatteryDTOArgumentCaptor.capture());
    }

    @Test
    void getListByRange() {
        //when
        batteryService.getListByRange("6000", "7000");
        //then
        Mockito.verify(batteryRepository).findAllByPostcodeIsBetweenOrderByNameAsc("6000", "7000");
    }
}
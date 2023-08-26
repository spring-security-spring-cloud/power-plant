package com.powerledger.powerplant.service;

import com.powerledger.powerplant.dto.BatteryDTO;
import com.powerledger.powerplant.model.Battery;

import java.util.List;
import java.util.Map;

public interface BatteryService {
    void createBatteries(List<BatteryDTO> batteries);

    Map<String, Object> getListByRange(String lowerRange, String upperRange);
}

package com.powerledger.powerplant.serviceImpl;

import com.powerledger.powerplant.dto.BatteryDTO;
import com.powerledger.powerplant.model.Battery;
import com.powerledger.powerplant.repository.BatteryRepository;
import com.powerledger.powerplant.service.BatteryService;
import com.powerledger.powerplant.util.BatteryUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BatteryServiceImpl implements BatteryService {
    private final BatteryRepository batteryRepository;
    @Override
    public void createBatteries(List<BatteryDTO> batteries) {
        List<Battery> batteriesEntity =
        batteries.stream().map(batteryDTO -> {
            Battery battery = new Battery();
            battery.setName(battery.getName());
            battery.setCapacity(battery.getCapacity());
            battery.setPostcode(battery.getPostcode());
            return battery;
        }).collect(Collectors.toList());

        batteryRepository.saveAll(batteriesEntity);
    }

    @Override
    public Map<String, Object> getListByRange(String lowerRange, String upperRange) {
        if (!upperRange.isEmpty() && !lowerRange.isEmpty()) {
            Map<String, Object> map = new HashMap<>();
            List<Battery> batteries = batteryRepository.findAllByPostcodeIsBetweenOrderByNameAsc(lowerRange, upperRange);
            map.put("batteries", batteries);
            map.put("totalCapacity", BatteryUtil.batteryStats(batteries).get("totalCapacity"));
            map.put("averageCapacity", BatteryUtil.batteryStats(batteries).get("averageCapacity"));
            return map;
        }

        throw new RuntimeException("lowerRange and upperRange can not be empty");
    }
}

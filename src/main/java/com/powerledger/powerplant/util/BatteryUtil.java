package com.powerledger.powerplant.util;

import com.powerledger.powerplant.model.Battery;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BatteryUtil {
    public static Map<String, Object> batteryStats(List<Battery> batteries) {
        Map<String, Object> stats = new HashMap<>();
        if (batteries.isEmpty()) {
            stats.put("totalCapacity", 0);
            stats.put("averageCapacity", 0);
            return stats;
        }

        Integer total = 0;
        float count = 0;
        for(Battery battery : batteries) {
            total += battery.getCapacity();
            count++;
        }
        Float average = total / count;
        stats.put("totalCapacity", total);
        stats.put("averageCapacity", average);

        return stats;
    }
}

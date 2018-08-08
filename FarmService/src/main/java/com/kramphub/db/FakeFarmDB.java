package com.kramphub.db;
import com.kramphub.rest.model.Alert;
import com.kramphub.rest.model.FarmAsset;
import com.kramphub.rest.model.Order;

import java.util.ArrayList;
import java.util.List;

public class FakeFarmDB {
    public static List<FarmAsset> farmAssets = new ArrayList<>();

    public static void initData(){
        farmAssets = new ArrayList<>();
        FarmAsset farmAsset1 = new FarmAsset();
        farmAsset1.setMachineId("tractor1");
        farmAsset1.setMachineModel("SOMEMODEL");
        farmAsset1.setMachineType("SOMETYPE");
        farmAsset1.setOperationHours(1222.6f);
        farmAsset1.setOperationKilometers(12227f);
        farmAsset1.setMachineName("Yellow Tractor");

//        List<Alert> alerts = new ArrayList<>();
//        alerts.add(new Alert("part1","broken","http://someurl"));
//        alerts.add(new Alert("part2","broken","http://someurl"));

//        farmAsset1.setAlerts(alerts);

        List<Order> orders = new ArrayList<>();
        orders.add(new Order("orderId1","part1", true));

        farmAsset1.setOrderHistory(orders);

        farmAssets.add(farmAsset1);
    }
}

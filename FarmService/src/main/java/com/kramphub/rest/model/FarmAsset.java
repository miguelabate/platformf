package com.kramphub.rest.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FarmAsset {
    private String machineId;
    private String machineType;
    private String machineModel;
    private String machineName;
    private Float operationKilometers;
    private Float operationHours;
    private List<Order> orderHistory;
    private List<Alert> alerts;
}

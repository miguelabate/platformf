package com.kramphub.rest.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Alert {
    private String alertId;
    private String partName;
    private String alertMessage;
    private String urlThumb;
    private String date;
}

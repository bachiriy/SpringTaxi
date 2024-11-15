package com.springtaxi.app.dto;

import com.springtaxi.app.entity.enums.DriverStatut;
import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Builder
@Data
public class DriverAnalytics {
    private double TauxOccupation;
    private Map<String,Integer> plagesHoraires;
    private Map<DriverStatut,Integer> repartitionStatuts;
}

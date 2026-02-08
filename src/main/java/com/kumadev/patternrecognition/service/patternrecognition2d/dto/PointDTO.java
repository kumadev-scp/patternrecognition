package com.kumadev.patternrecognition.service.patternrecognition2d.dto;

import lombok.AllArgsConstructor;
import lombok.Value;

/**
 * Using Data Transfer Object pattern to separate service interfaces structures nature from database entities
 */
@Value
@AllArgsConstructor
public class PointDTO {
    private int x;
    private int y;
}

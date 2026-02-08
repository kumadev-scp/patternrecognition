package com.kumadev.patternrecognition.service.patternrecognition2d.dto;

import com.kumadev.patternrecognition.model.point.Point;
import org.springframework.stereotype.Component;

/**
 * Mapper from {@link Point} to {@link Point DTO}
 */
@Component
public class PointMapper {
    public PointDTO toDTO(Point p){
        return new PointDTO(p.getX(), p.getY());
    }

    public Point toPoint(PointDTO dto){
        return new Point(dto.getX(), dto.getY());
    }
}

package com.kumadev.patternrecognition.service;

import com.kumadev.patternrecognition.model.Point;
import com.kumadev.patternrecognition.service.patternrecognition2d.dto.PointDTO;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface PatternRecognitionService {

    Set<Set<Point>> getLinesPerNPoints(int n);

    Set<Point> getSpacePoints();

    void deleteAllSpacePoints();

    Point addPointToSpace(PointDTO p);
}

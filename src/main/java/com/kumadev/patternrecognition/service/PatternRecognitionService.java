package com.kumadev.patternrecognition.service;

import com.kumadev.patternrecognition.model.point.Point;
import com.kumadev.patternrecognition.service.patternrecognition2d.dto.PointDTO;

import java.util.Set;

public interface PatternRecognitionService {

    /**
     * Get all line segments passing through at least N points. A line segment is a set of
     * points.
     *
     * @param n number of minimum points belonged to the line we want as result
     * @return
     */
    Set<Set<Point>> getLinesPerNPoints(int n);

    /**
     * Get all points in the space
     *
     * @return
     */
    Set<Point> getSpacePoints();

    /**
     *Remove all points from the space
     */
    void deleteAllSpacePoints();

    /**
     * Add a point to the space
     *
     * @param p
     * @return
     */
    Point addPointToSpace(PointDTO p);
}

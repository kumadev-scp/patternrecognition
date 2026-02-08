package com.kumadev.patternrecognition.data;

import com.kumadev.patternrecognition.model.Point;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PointRepository extends CrudRepository<Point, Long> {
}

package com.kumadev.patternrecognition.data;

import com.kumadev.patternrecognition.model.point.Point;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface PointRepository extends CrudRepository<Point, Long> {

    Set<Point> findByXAndY(int x, int y);
}

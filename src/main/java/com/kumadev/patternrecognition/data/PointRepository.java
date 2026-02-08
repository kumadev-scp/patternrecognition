package com.kumadev.patternrecognition.data;

import com.kumadev.patternrecognition.model.point.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface PointRepository extends JpaRepository<Point, Long> {

    Set<Point> findByXAndY(int x, int y);
}

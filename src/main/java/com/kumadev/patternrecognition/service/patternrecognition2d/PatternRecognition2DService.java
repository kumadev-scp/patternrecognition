package com.kumadev.patternrecognition.service.patternrecognition2d;

import com.kumadev.patternrecognition.data.PointRepository;
import com.kumadev.patternrecognition.model.point.Point;
import com.kumadev.patternrecognition.model.point.validation.AlreadyExistingPointException;
import com.kumadev.patternrecognition.service.PatternRecognitionService;
import com.kumadev.patternrecognition.service.patternrecognition2d.dto.PointDTO;
import lombok.RequiredArgsConstructor;
import org.antlr.v4.runtime.misc.OrderedHashSet;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Implementation of the Pattern Recognition service considering integer two-dimensional space
 */
@RequiredArgsConstructor
@Service
public class PatternRecognition2DService implements PatternRecognitionService {

    private final PointRepository pointRepository;

    @Override
    public Set<Set<Point>> getLinesPerNPoints(int n) {
        Set<Set<Point>> result = new HashSet<>();
        Set<Point> space = getSpacePoints();

        if(space.size() < n){
            return result;
        }

        Map<String, Set<Point>> slopeMap;
        // taking every point as pivot...
        for(Point pivot : space){
            //... calculate the slope generated using each other points in the space
            slopeMap = pivot.getCollinearPoints(space.stream().filter(point -> !point.equals(pivot)).collect(Collectors.toSet()));
            if(!slopeMap.isEmpty()){
                for(Set<Point> line : slopeMap.values()){
                    if(line.size() + 1 >= n){
                        line.add(pivot);
                        result.add(line);
                    }
                }
            }
        }

        return result;
    }

    @Override
    public Set<Point> getSpacePoints() {
        return pointRepository.findAll().stream().sorted().collect(Collectors.toCollection(OrderedHashSet::new));
    }

    @Override
    public void deleteAllSpacePoints() {
        pointRepository.deleteAll();
    }

    @Override
    public Point addPointToSpace(PointDTO dto) {
        Set<Point> p = pointRepository.findByXAndY(dto.getX(),dto.getY());
        if(!p.isEmpty()){
            throw new AlreadyExistingPointException("This point is already present inside the space");
        }
        return pointRepository.save(new Point(dto.getX(), dto.getY()));
    }
}

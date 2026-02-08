package com.kumadev.patternrecognition.service.patternrecognition2d;

import com.kumadev.patternrecognition.data.PointRepository;
import com.kumadev.patternrecognition.model.Point;
import com.kumadev.patternrecognition.service.PatternRecognitionService;
import com.kumadev.patternrecognition.service.patternrecognition2d.dto.PointDTO;
import lombok.RequiredArgsConstructor;
import org.antlr.v4.runtime.misc.OrderedHashSet;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

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

        Map<String, Set<Point>> slopeMap = new HashMap<>();

        for(Point pivot : space){
            slopeMap = getCollinearPoints(pivot, space.stream().filter(point -> !point.equals(pivot)).collect(Collectors.toSet()));
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

    private Map<String, Set<Point>> getCollinearPoints(Point pivot, Set<Point> otherPoints) {
        Map<String, Set<Point>> slopes = new HashMap<>();

        for(Point p : otherPoints){
            String slope = pivot.getSlope(p);

            slopes.putIfAbsent(slope, new HashSet<>());
            slopes.get(slope).add(p);
        }

        return slopes;
    }

    @Override
    public Set<Point> getSpacePoints() {
        return Streamable.of(pointRepository.findAll()).toSet();
    }

    @Override
    public void deleteAllSpacePoints() {
        pointRepository.deleteAll();
    }

    @Override
    public Point addPointToSpace(PointDTO p) {
        return pointRepository.save(new Point(p.getX(), p.getY()));
    }
}

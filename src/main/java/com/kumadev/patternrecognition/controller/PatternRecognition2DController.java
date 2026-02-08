package com.kumadev.patternrecognition.controller;

import com.kumadev.patternrecognition.MathUtils;
import com.kumadev.patternrecognition.model.point.validation.AlreadyExistingPointException;
import com.kumadev.patternrecognition.service.patternrecognition2d.PatternRecognition2DService;
import com.kumadev.patternrecognition.service.patternrecognition2d.dto.PointDTO;
import com.kumadev.patternrecognition.service.patternrecognition2d.dto.PointMapper;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequestMapping("/api/2dint/v1/")
@RestController
public class PatternRecognition2DController {

    @Autowired
    PatternRecognition2DService patternRecognitionService;

    @Autowired
    PointMapper pointMapper;

    @PostMapping(path = "/point")
    public void addPoint(@RequestBody PointDTO point) {
        patternRecognitionService.addPointToSpace(point);
    }

    @GetMapping(path = "/lines/{n}")
    public List<Set<PointDTO>> linesPerPoint(@PathVariable @Min(MathUtils.LINE_MIN_POINT_NUMBER) Integer n) {
        return patternRecognitionService.getLinesPerNPoints(n)
                .stream()
                .map(points -> points
                        .stream()
                        .map(pointMapper::toDTO).collect(Collectors.toSet()))
                .collect(Collectors.toList());
    }

    @GetMapping(path = "/space")
    public Set<PointDTO> getAllPoints() {
        return patternRecognitionService.getSpacePoints().stream().map(pointMapper::toDTO).collect(Collectors.toSet());
    }

    @DeleteMapping(path = "/space")
    public void deleteAllPoints() {
        patternRecognitionService.deleteAllSpacePoints();
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private void pointNotValidHandler(AlreadyExistingPointException ex) {}
}

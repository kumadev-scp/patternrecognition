package com.kumadev.patternrecognition.model.point;

import com.kumadev.patternrecognition.MathUtils;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

/**
 * Representation of integer point in the plane
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Point implements Comparable<Point> {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private int x;
    @NotNull
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int compareTo(Point p) {
        if (this.y != p.y) return Integer.compare(this.y, p.y);
        return Integer.compare(this.x, p.x);
    }

    /**
     * Calculate the slope between the given point and this instance.
     * Expressed in form of String to avoid double periodic numbers approximation.
     * The slope is normalized using the Great Common Divisor for the deltas
     *
     * @param p {@link Point} the point from which calculate the slope
     * @return slope expressed in String representation : "y/x"
     */
    public String getSlope(Point p){
        int dy = p.y - this.y;
        int dx = p.x - this.x;

        int gcd = MathUtils.greatCommonDivisor(dy, dx);

        return dy/gcd + "/" + dx/gcd;
    }

    /**
     * Collects points in the space collapsing into a map using the slope from pivot
     *
     * @param otherPoints set including every other points in the space
     * @return Map of points (key is the slope, value is the set of points sharing the same slope)
     */
    public Map<String, Set<Point>> getCollinearPoints(Set<Point> otherPoints) {
        Map<String, Set<Point>> slopes = new HashMap<>();

        for(Point p : otherPoints){
            String slope = this.getSlope(p);

            slopes.putIfAbsent(slope, new HashSet<>());
            slopes.get(slope).add(p);
        }

        return slopes;
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return x == point.x && y == point.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
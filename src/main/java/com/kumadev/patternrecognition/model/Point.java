package com.kumadev.patternrecognition.model;

import com.kumadev.patternrecognition.GeometryUtils;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Point implements Comparable<Point> {

    @Id
    @GeneratedValue
    private Long id;

    private int x;
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

        int gcd = GeometryUtils.greatCommonDivisor(dy, dx);

        return dy/gcd + "/" + dx/gcd;
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
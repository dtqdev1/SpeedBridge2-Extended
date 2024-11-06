package io.tofpu.speedbridge2;

import io.tofpu.speedbridge2.util.ClosestNumberFinder;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ClosestNumberFinderTest {
    @Test
    void name() {
        List<Integer> widths = Arrays.asList(100, 200, 300, 400);

        assertEquals(100, ClosestNumberFinder.findClosest(99, widths));
        assertEquals(100, ClosestNumberFinder.findClosest(100, widths));

        assertEquals(200, ClosestNumberFinder.findClosest(101, widths));
        assertEquals(200, ClosestNumberFinder.findClosest(199, widths));
        assertEquals(200, ClosestNumberFinder.findClosest(200, widths));

        assertEquals(300, ClosestNumberFinder.findClosest(201, widths));
        assertEquals(300, ClosestNumberFinder.findClosest(299, widths));
        assertEquals(300, ClosestNumberFinder.findClosest(300, widths));

        assertEquals(400, ClosestNumberFinder.findClosest(301, widths));
        assertEquals(400, ClosestNumberFinder.findClosest(399, widths));
        assertEquals(400, ClosestNumberFinder.findClosest(400, widths));

        assertNull(ClosestNumberFinder.findClosest(401, widths));
    }
}

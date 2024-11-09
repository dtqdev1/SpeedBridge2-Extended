package io.tofpu.speedbridge2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import io.tofpu.speedbridge2.util.ClosestNumberFinder;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

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

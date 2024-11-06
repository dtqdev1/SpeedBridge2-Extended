package io.tofpu.speedbridge2.arena;

import io.tofpu.speedbridge2.util.Position;

public interface PositionCalculator<K> {
    /**
     * Reserves a position for an arena with the given key and width. The returned position will be marked as occupied
     * and cannot be used for another arena.
     *
     * @param key   the key used for identifying the arena
     * @param width the width of the arena in blocks (x-axis)
     * @return the position of the arena
     */
    Position reserve(K key, int width);

    /**
     * Releases a position for an arena with the given key. The position will be marked as free and available for
     * other arenas.
     *
     * @param key the key used for identifying the arena
     */
    void release(K key);
}

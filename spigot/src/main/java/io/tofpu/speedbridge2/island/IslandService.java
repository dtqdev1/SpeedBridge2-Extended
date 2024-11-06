package io.tofpu.speedbridge2.island;

import java.util.HashMap;
import java.util.Map;

public class IslandService {
    private final Map<Integer, Island> islandMap = new HashMap<>();

    public void registerIsland(Island island) {
        islandMap.put(island.slot(), island);
    }

    public Island getIsland(int slot) {
        return islandMap.get(slot);
    }
}

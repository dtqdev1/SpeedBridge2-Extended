package com.github.tofpu.speedbridge2.common.setup.event;

import com.github.tofpu.speedbridge2.common.setup.IslandSetup;

public class StopIslandSetupEvent extends IslandSetupEvent {
    public StopIslandSetupEvent(IslandSetup islandSetup) {
        super(islandSetup);
    }
}

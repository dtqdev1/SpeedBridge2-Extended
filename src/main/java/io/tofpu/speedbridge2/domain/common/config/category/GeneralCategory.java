package io.tofpu.speedbridge2.domain.common.config.category;

import org.spongepowered.configurate.objectmapping.ConfigSerializable;
import org.spongepowered.configurate.objectmapping.meta.Comment;
import org.spongepowered.configurate.objectmapping.meta.Setting;

@ConfigSerializable
public final class GeneralCategory {

    @Setting("default-island-category")
    @Comment("This will set the default island category when they're not provided upon " +
             "creation")
    private String defaultIslandCategory = "standard";

    public String getDefaultIslandCategory() {
        return defaultIslandCategory;
    }
}

package io.tofpu.speedbridge2.game.config;

import io.tofpu.speedbridge2.game.config.experience.GamePlayerExperienceConfiguration;
import io.tofpu.speedbridge2.game.config.item.GameItemConfiguration;
import space.arim.dazzleconf.annote.ConfDefault;
import space.arim.dazzleconf.annote.SubSection;

public interface GameConfiguration {
    @SubSection
    @ConfDefault.DefaultObject("io.tofpu.speedbridge2.game.config.GameConfigDefaults.item")
    GameItemConfiguration item();

    @SubSection
    @ConfDefault.DefaultObject("io.tofpu.speedbridge2.game.config.GameConfigDefaults.experience")
    GamePlayerExperienceConfiguration experience();
}

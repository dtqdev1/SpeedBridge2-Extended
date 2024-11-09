package io.tofpu.speedbridge2.game.config;

import static io.tofpu.speedbridge2.game.config.experience.GamePlayerExperienceConfiguration.GameOptions;
import static io.tofpu.speedbridge2.game.config.experience.GamePlayerExperienceConfiguration.builder;

import com.cryptomorin.xseries.XMaterial;
import com.cryptomorin.xseries.XSound;
import io.tofpu.speedbridge2.game.config.arena.GameArenaConfiguration;
import io.tofpu.speedbridge2.game.config.experience.GamePlayerExperienceConfiguration;
import io.tofpu.speedbridge2.game.config.item.GameItemConfiguration;
import io.tofpu.speedbridge2.util.ColorUtil;
import io.tofpu.speedbridge2.util.ItemStackBuilder;

/**
 * This class is responsible for housing the default configuration values for {@link GameConfiguration}.
 */
public class GameConfigDefaults {
    public static GameArenaConfiguration arena() {
        return GameArenaConfiguration.of(100);
    }

    public static GamePlayerExperienceConfiguration experience() {
        return builder()
                .reset(GameOptions.builder()
                        .sound(XSound.UI_BUTTON_CLICK, 1, 1)
                        .addMessages("Game has been reset")
                        .build())
                .score(GameOptions.builder()
                        .sound(XSound.UI_BUTTON_CLICK, 1, 1)
                        .title("Game Scored", "Game has been scored")
                        .addMessages("It took %time% to reach the end.")
                        .build())
                .beatenScore(GameOptions.builder()
                        .sound(XSound.UI_BUTTON_CLICK, 1, 1)
                        .title("Game Beaten", "Game has been beaten")
                        .addMessages(
                                "You broke your personal best score of %time%",
                                "Your new personal best score is %time%")
                        .build())
                .build();
    }

    public static GameItemConfiguration item() {
        return GameItemConfiguration.builder()
                .resetGame(
                        ItemStackBuilder.newBuilder()
                                .displayName(ColorUtil.colorize("&eReset"))
                                .lore(ColorUtil.colorize("&7Click to leave the game"))
                                .apply(XMaterial.RED_DYE.parseItem()),
                        7)
                .leaveGame(
                        ItemStackBuilder.newBuilder()
                                .displayName(ColorUtil.colorize("&eLeave"))
                                .lore(ColorUtil.colorize("&7Click to leave the game"))
                                .apply(XMaterial.RED_BED.parseItem()),
                        8)
                .build();
    }
}

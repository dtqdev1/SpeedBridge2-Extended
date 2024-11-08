package io.tofpu.speedbridge2.game.config.experience;

import io.tofpu.speedbridge2.game.config.experience.meta.Sound;
import io.tofpu.speedbridge2.game.config.experience.meta.Title;
import io.tofpu.speedbridge2.util.ColorUtil;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import space.arim.dazzleconf.annote.ConfComments;
import space.arim.dazzleconf.annote.SubSection;

import java.util.List;

import static org.immutables.value.Value.Immutable;

/**
 * This class is responsible for customizing the player experience. Like, when the game is reset, scored, or a new personal best score is achieved.
 */
@Immutable
public interface GamePlayerExperienceConfiguration {
    @SubSection
    @ConfComments("Options to customize the player experience when the game is reset")
    GameOptions reset();

    @SubSection
    @ConfComments("Options to customize the player experience when they score")
    GameOptions score();

    @SubSection
    @ConfComments("Options to customize the player experience when they surpass their personal best score")
    GameOptions beatenScore();

    static ImmutableGamePlayerExperienceConfiguration.Builder builder() {
        return ImmutableGamePlayerExperienceConfiguration.builder();
    }

    @Immutable
    interface GameOptions {

        static ImmutableGameOptions.Builder builder() {
            return ImmutableGameOptions.builder();
        }

        @SubSection
        Sound sound();
        @SubSection
        Title title();

        List<String> commands();
        List<String> messages();

        default void apply(Player player) {
            sound().play(player);
            title().show(player);
            sendMessages(player);
            performCommands(player);
        }

        default void sendMessages(Player player) {
            // apply placeholders to the commands before sending
            for (String message : messages()) {
                player.sendMessage(ColorUtil.colorize(message));
            }
        }

        default void performCommands(Player player) {
            // apply placeholders to the commands before dispatching
            for (String command : commands()) {
                Server server = Bukkit.getServer();
                server.dispatchCommand(server.getConsoleSender(), command);
            }
        }
    }
}

package io.tofpu.speedbridge2.game.command;

import io.tofpu.speedbridge2.command.ChildrenCommand;
import io.tofpu.speedbridge2.command.CommandHandler;
import io.tofpu.speedbridge2.game.GameService;
import io.tofpu.speedbridge2.island.Island;
import org.bukkit.entity.Player;
import revxrsal.commands.annotation.Command;
import revxrsal.commands.bukkit.actor.BukkitCommandActor;
import revxrsal.commands.command.CommandActor;
import revxrsal.commands.orphan.OrphanCommand;

@Command("game")
public class GameCommand extends ChildrenCommand {
    private final GameService gameService;

    public GameCommand(GameService gameService) {
        this.gameService = gameService;
    }

    @Command("join")
    public void joinGame(BukkitCommandActor actor, Island island) {
        actor.reply(String.format("&eJoining %d island now...", island.slot()));
        if (gameService.startGame(actor.requirePlayer(), island)) {
            actor.reply(String.format("You joined island %d", island.slot()));
        } else {
            actor.reply("&cYou're already in a game!");
        }
    }

    @Command("leave")
    public void leaveGame(BukkitCommandActor actor) {
        if (gameService.stopGame(actor.requirePlayer())) {
            actor.reply("&eYou left the game");
        } else {
            actor.reply("&cYou're not in a game!");
        }
    }
}

package io.tofpu.speedbridge2.island.command;

import io.tofpu.speedbridge2.command.ChildrenCommand;
import io.tofpu.speedbridge2.island.Island;
import io.tofpu.speedbridge2.island.IslandService;
import revxrsal.commands.annotation.Command;
import revxrsal.commands.annotation.Subcommand;
import revxrsal.commands.command.CommandActor;

import java.util.Collection;

@Command("island")
public class IslandCommand extends ChildrenCommand {
    private final IslandService islandService;

    public IslandCommand(IslandService islandService) {
        this.islandService = islandService;
    }

    private static String islandInfo(Island island) {
        return String.format(
                "&7Island: &f%s\n" +
                "&8-> &7Schematic = &f%s\n",
                island.slot(),
                island.schematic().name()
        );
    }

    @Subcommand("remove")
    public void removeIsland(CommandActor actor, Island island) {
        islandService.removeIsland(island.slot());
        actor.reply("&eRemoved island &f" + island.slot());
    }

    @Command("list")
    public void listIslands(CommandActor actor) {
        Collection<Island> islands = islandService.islands();
        if (islands.isEmpty()) {
            actor.reply("&cNo registered islands found.");
            return;
        }
        actor.reply("&eList of registered islands:");

        int number = 1;
        for (Island island : islands) {
            actor.reply(String.format("%d) %s", number, islandInfo(island)));
            number++;
        }
    }
}

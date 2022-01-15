package io.tofpu.speedbridge2.command;

import io.tofpu.speedbridge2.database.Databases;
import io.tofpu.speedbridge2.domain.Island;
import io.tofpu.speedbridge2.domain.game.GameIsland;
import io.tofpu.speedbridge2.domain.game.GamePlayer;
import io.tofpu.speedbridge2.domain.service.IslandService;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PluginCommand implements CommandExecutor {

    @Override
    public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args) {
        final IslandService service = IslandService.INSTANCE;

        GamePlayer gamePlayer;
        Island island;
        switch (args[0]) {
            case "create":
                if (service.createIsland(Integer.parseInt(args[1])) == null) {
                    sender.sendMessage(args[1] + " island already exists");
                    return false;
                }
                sender.sendMessage("created island on " + args[1] + " slot");
                break;
            case "select":
                island = service.findIslandBy(Integer.parseInt(args[1]));
                final boolean foundSchematic = island.selectSchematic(args[2]);

                if (foundSchematic) {
                    sender.sendMessage("you have successfully selected a schematic");
                } else {
                    sender.sendMessage("the schematic couldn't be found");
                }
                break;
            case "join":
                gamePlayer = GamePlayer.of((Player) sender);
                if (gamePlayer.isPlaying()) {
                    sender.sendMessage("you already joined an island");
                    return false;
                }
                island = service.findIslandBy(Integer.parseInt(args[1]));
                island.generateGame((Player) sender);
                break;
            case "leave":
                gamePlayer = GamePlayer.of((Player) sender);
                if (!gamePlayer.isPlaying()) {
                    sender.sendMessage("you're not playing");
                    return false;
                }
                island = service.findIslandBy(gamePlayer.getIslandSlot());
                island.leaveGame(gamePlayer);
                break;
            case "change":
                island = service.findIslandBy(Integer.parseInt(args[1]));
                if (island == null) {
                    sender.sendMessage(args[1] + " island cannot be found");
                    return false;
                }
                island.setCategory(args[2]);
                sender.sendMessage("updated island");
                break;
            case "delete":
                if (service.deleteIsland(Integer.parseInt(args[1])) == null) {
                    sender.sendMessage(args[1] + " island cannot be found");
                    return false;
                }
                sender.sendMessage("deleted island on " + args[1] + " slot");
                break;
            case "islands":
                sender.sendMessage("here's your locally stored islands");
                sender.sendMessage(service.getAllIslands().toString());
                break;
            case "data":
                Databases.ISLAND_DATABASE.getStoredIslands()
                        .whenComplete((islands, throwable) -> {
                            sender.sendMessage("here's your database stored islands");
                            sender.sendMessage(islands.toString());
                        });
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + args[0]);
        }

        return false;
    }
}

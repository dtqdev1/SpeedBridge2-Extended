package io.tofpu.speedbridge2.lobby.command;

import io.tofpu.speedbridge2.command.ChildrenCommand;
import io.tofpu.speedbridge2.lobby.LobbyService;
import revxrsal.commands.annotation.CommandPlaceholder;
import revxrsal.commands.annotation.Subcommand;
import revxrsal.commands.bukkit.actor.BukkitCommandActor;

@Subcommand("lobby")
public class LobbyCommand extends ChildrenCommand {
    private final LobbyService lobbyService;

    public LobbyCommand(LobbyService lobbyService) {
        this.lobbyService = lobbyService;
    }

    @Subcommand("set")
    public void setLobby(BukkitCommandActor actor) {
        lobbyService.lobby(actor.requirePlayer().getLocation());
        actor.reply(
                String.format("&aThe lobby has been set to your current location: &2[&f%s&2, &f%s&2, &f%s&2]",
                        actor.requirePlayer().getLocation().getX(),
                        actor.requirePlayer().getLocation().getY(),
                        actor.requirePlayer().getLocation().getZ()
                )
        );
    }

    @Subcommand("teleport")
    @CommandPlaceholder // need to verify if this still works;
    // as I was hoping both `... lobby` (no arguments) and `... lobby teleport` would both work
    public void teleportToLobby(BukkitCommandActor actor) {
        actor.requirePlayer().teleport(lobbyService.lobby());
    }

    @Subcommand("get")
    public void getLobby(BukkitCommandActor actor) {
        actor.reply(String.format("&aThe lobby is at: &2[&f%s&2, &f%s&2, &f%s&2]",
                lobbyService.lobby().getX(),
                lobbyService.lobby().getY(),
                lobbyService.lobby().getZ()
        ));
    }
}

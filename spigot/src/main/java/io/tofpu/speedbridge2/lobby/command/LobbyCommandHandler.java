package io.tofpu.speedbridge2.lobby.command;

import io.tofpu.speedbridge2.command.CommandHandler;
import io.tofpu.speedbridge2.lobby.LobbyService;

public class LobbyCommandHandler {
    private final CommandHandler commandHandler;
    private final LobbyService lobbyService;

    public static void init(CommandHandler commandHandler, LobbyService lobbyService) {
        new LobbyCommandHandler(commandHandler, lobbyService).register();
    }

    private LobbyCommandHandler(CommandHandler commandHandler, LobbyService lobbyService) {
        this.commandHandler = commandHandler;
        this.lobbyService = lobbyService;
    }

    public void register() {
        commandHandler.addChildCommand(new LobbyCommand(lobbyService));
    }
}

package io.tofpu.speedbridge2.command;

import revxrsal.commands.annotation.Default;
import revxrsal.commands.annotation.Range;
import revxrsal.commands.annotation.Subcommand;
import revxrsal.commands.command.CommandActor;
import revxrsal.commands.command.ExecutableCommand;
import revxrsal.commands.help.Help;
import revxrsal.commands.orphan.OrphanCommand;

import java.util.List;

public class ChildrenCommand implements OrphanCommand {
    private static final int ENTRIES_PER_PAGE = 7;

    @Subcommand("help")
    public void sendHelpMenu(
            CommandActor actor,
            @Range(min = 1) @Default("1") int page,
            Help.SiblingCommands<CommandActor> commands) {
        List<ExecutableCommand<CommandActor>> list = commands.paginate(page, ENTRIES_PER_PAGE);
        for (ExecutableCommand<CommandActor> command : list) {
            actor.reply("- " + command.usage());
        }
    }
}

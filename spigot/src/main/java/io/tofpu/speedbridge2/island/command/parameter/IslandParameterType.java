package io.tofpu.speedbridge2.island.command.parameter;

import io.tofpu.speedbridge2.island.Island;
import io.tofpu.speedbridge2.island.IslandService;
import org.jetbrains.annotations.NotNull;
import revxrsal.commands.autocomplete.SuggestionProvider;
import revxrsal.commands.bukkit.actor.BukkitCommandActor;
import revxrsal.commands.exception.CommandErrorException;
import revxrsal.commands.node.ExecutionContext;
import revxrsal.commands.parameter.ParameterType;
import revxrsal.commands.stream.MutableStringStream;

import java.util.stream.Collectors;

public class IslandParameterType implements ParameterType<BukkitCommandActor, Island> {
    private final IslandService islandService;

    public IslandParameterType(IslandService islandService) {
        this.islandService = islandService;
    }

    @Override
    public Island parse(@NotNull MutableStringStream input, @NotNull ExecutionContext<@NotNull BukkitCommandActor> context) {
        int slot = input.readInt();
        Island island = islandService.getIsland(slot);
        if (island == null) {
            throw new CommandErrorException("No such island: " + slot);
        }
        return island;
    }

    @Override
    public @NotNull SuggestionProvider<@NotNull BukkitCommandActor> defaultSuggestions() {
        return context -> islandService.islands().stream()
                .map(Island::slot)
                .map(String::valueOf)
                .collect(Collectors.toList());
    }
}

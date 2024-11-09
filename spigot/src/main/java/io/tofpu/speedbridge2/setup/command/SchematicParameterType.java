package io.tofpu.speedbridge2.setup.command;

import io.tofpu.speedbridge2.schematic.Schematic;
import io.tofpu.speedbridge2.schematic.SchematicService;
import org.jetbrains.annotations.NotNull;
import revxrsal.commands.autocomplete.SuggestionProvider;
import revxrsal.commands.bukkit.actor.BukkitCommandActor;
import revxrsal.commands.exception.CommandErrorException;
import revxrsal.commands.node.ExecutionContext;
import revxrsal.commands.parameter.ParameterType;
import revxrsal.commands.stream.MutableStringStream;

public class SchematicParameterType implements ParameterType<BukkitCommandActor, Schematic> {
    private final SchematicService schematicService;

    public SchematicParameterType(SchematicService schematicService) {
        this.schematicService = schematicService;
    }

    @Override
    public Schematic parse(@NotNull MutableStringStream input, @NotNull ExecutionContext<@NotNull BukkitCommandActor> context) {
        String schematicName = input.readString();
        Schematic schematic = schematicService.resolveSchematic(schematicName);
        if (schematic == null) {
            throw new CommandErrorException("&cCould not find schematic with name " + schematicName);
        }
        return schematic;
    }

    @Override
    public @NotNull SuggestionProvider<@NotNull BukkitCommandActor> defaultSuggestions() {
        return context -> schematicService.schematicNames();
    }
}

package io.tofpu.speedbridge2.setup;

import io.tofpu.speedbridge2.arena.Arena;
import io.tofpu.speedbridge2.arena.ArenaManager;
import io.tofpu.speedbridge2.arena.DefaultPositionCalculator;
import io.tofpu.speedbridge2.island.Island;
import io.tofpu.speedbridge2.island.IslandService;
import io.tofpu.speedbridge2.schematic.Schematic;
import io.tofpu.speedbridge2.schematic.SchematicService;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.UUID;

public class SetupService {
    private final SchematicService schematicService;
    private final IslandService islandService;
    private final ArenaManager<UUID> arenaManager;

    public SetupService(SchematicService schematicService, IslandService islandService, World world) {
        this.schematicService = schematicService;
        this.islandService = islandService;
//        this.arenaManager = new ArenaManager<UUID>(world, USE_A_CLASS_COSNTANTS);
        this.arenaManager = new ArenaManager<>(world, new DefaultPositionCalculator<>(0, 0, 0, () -> 0));
    }

    public void createSetup(Player player, SetupInfo setupInfo) {
        // todo: handle the exceptions
        Schematic schematic = schematicService.resolveSchematic(setupInfo.schematicName());

        UUID playerId = player.getUniqueId();
        Arena arena = arenaManager.generateArena(playerId, schematic);
        if (arena == null) {
            throw new IllegalStateException("Could not create arena!");
        }

        IslandSetup setup = new IslandSetup(this, player, setupInfo.slot(), schematic, arena);
        setup.start();
    }

    public void finishSetup(IslandSetup setup) {
        cleanUp(setup.player());
        if (!setup.canBeFinished()) {
            // todo: throw exception here? as it's unexpected
            return;
        }

        Island island = new Island(setup.slot(), setup.schematic(), setup.spawnPoint());
        islandService.registerIsland(island);
    }

    public void cleanUp(Player player) {
        arenaManager.destroyArena(player.getUniqueId(), () -> teleportPlayerToLobby(player));
    }

    private void teleportPlayerToLobby(Player player) {
        // todo: lobbyLocation, if not available, teleport to world at 0 index default spawn
//        player.teleport(lobbyLocation);
    }
}

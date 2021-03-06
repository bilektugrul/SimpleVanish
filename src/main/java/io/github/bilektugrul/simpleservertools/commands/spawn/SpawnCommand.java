package io.github.bilektugrul.simpleservertools.commands.spawn;

import io.github.bilektugrul.simpleservertools.SST;
import io.github.bilektugrul.simpleservertools.features.spawn.Spawn;
import io.github.bilektugrul.simpleservertools.features.spawn.SpawnManager;
import io.github.bilektugrul.simpleservertools.stuff.teleporting.Mode;
import io.github.bilektugrul.simpleservertools.stuff.teleporting.TeleportManager;
import io.github.bilektugrul.simpleservertools.stuff.teleporting.TeleportMode;
import io.github.bilektugrul.simpleservertools.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class SpawnCommand implements CommandExecutor {

    private final SpawnManager spawnManager;
    private final TeleportManager teleportManager;

    public SpawnCommand(SST plugin) {
        this.spawnManager = plugin.getSpawnManager();
        this.teleportManager = plugin.getTeleportManager();
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (!sender.hasPermission("sst.spawn")) {
            sender.sendMessage(Utils.getMessage("no-permission", sender));
            return true;
        }

        if (!spawnManager.isEnabled()) {
            spawnManager.sendWarnIfEnabled(sender);
            return true;
        }

        if (!spawnManager.isPresent()) {
            sender.sendMessage(Utils.getMessage("spawn.spawn-not-set", sender));
            return true;
        }

        boolean argPresent = args.length > 0;

        if (argPresent && !sender.hasPermission("sst.spawn.others")) {
            sender.sendMessage(Utils.getMessage("no-permission", sender));
            return true;
        }

        Spawn spawn = spawnManager.getSpawn();
        TeleportMode mode = new TeleportMode(Mode.SPAWN, null, spawn, null);
        final Location loc = spawn.getLocation();

        Player toTeleport = argPresent ? Bukkit.getPlayer(args[0]) : sender instanceof Player ? (Player) sender : null;

        if (toTeleport == null) {
            sender.sendMessage(Utils.getMessage("spawn.player-not-found", sender));
            return true;
        }

        teleportManager.teleport(toTeleport, loc, mode, spawnManager.getSettings());
        return true;
    }

}

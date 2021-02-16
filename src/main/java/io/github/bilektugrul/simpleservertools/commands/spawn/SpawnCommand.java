package io.github.bilektugrul.simpleservertools.commands.spawn;

import io.github.bilektugrul.simpleservertools.SimpleServerTools;
import io.github.bilektugrul.simpleservertools.features.spawn.SpawnManager;
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

    public SpawnCommand(SimpleServerTools plugin) {
        this.spawnManager = plugin.getSpawnManager();
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (spawnManager.isEnabled()) {
            if (spawnManager.isPresent()) {
                final Location loc = spawnManager.getSpawn().getLocation();
                if (args.length == 1) {
                    Player toTeleport = Bukkit.getPlayer(args[0]);
                    if (toTeleport != null) Utils.teleport(toTeleport, loc, "spawn");
                    else sender.sendMessage(Utils.getPAPILessString("other-messages.spawn.player-not-found", sender));
                } else if (args.length == 0 && sender instanceof Player) {
                    Utils.teleport((Player) sender, loc, "spawn");
                }
            } else {
                sender.sendMessage(Utils.getPAPILessString("other-messages.spawn.spawn-not-set", sender));
            }
        } else {
            spawnManager.sendWarnIfEnabled(sender);
        }
        return true;
    }

}

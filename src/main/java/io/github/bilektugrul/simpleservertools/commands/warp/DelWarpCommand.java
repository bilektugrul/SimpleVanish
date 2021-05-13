package io.github.bilektugrul.simpleservertools.commands.warp;

import io.github.bilektugrul.simpleservertools.SST;
import io.github.bilektugrul.simpleservertools.features.warps.Warp;
import io.github.bilektugrul.simpleservertools.features.warps.WarpManager;
import io.github.bilektugrul.simpleservertools.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class DelWarpCommand implements CommandExecutor {

    private final WarpManager warpManager;

    public DelWarpCommand(SST plugin) {
        this.warpManager = plugin.getWarpManager();
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (!sender.hasPermission("sst.admin")) {
            sender.sendMessage(Utils.getString("no-permission", sender));
            return true;
        }

        if (args.length == 0) {
            sender.sendMessage(Utils.getString("other-messages.warps.not-enough-arguments", sender));
            return true;
        }

        Warp warp = warpManager.getWarp(args[0]);

        if (warp == null) {
            sender.sendMessage(Utils.getString("other-messages.warps.do-not-exist", sender));
            return true;
        }

        String warpName = warp.getName();

        warpManager.sendWarpInfo(warp, sender);
        sender.sendMessage(Utils.getString("other-messages.warps.deleting", sender)
                .replace("%warp%", warpName));
        warpManager.deleteWarp(warp);
        sender.sendMessage(Utils.getString("other-messages.warps.deleted", sender)
                .replace("%warp%", warpName));
        return true;
    }

}
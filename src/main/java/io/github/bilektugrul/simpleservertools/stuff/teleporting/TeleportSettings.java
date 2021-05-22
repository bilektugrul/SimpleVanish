package io.github.bilektugrul.simpleservertools.stuff.teleporting;

import io.github.bilektugrul.simpleservertools.stuff.CancelMode;

public class TeleportSettings {

    private final int time;
    private final CancelMode cancelMoveMode, cancelDamageMode, cancelCommandsMode;
    private final boolean blockMove, cancelTeleportOnMove, blockDamage, cancelTeleportOnDamage, staffBypassTime, blockCommands;

    public TeleportSettings(int time, boolean blockMove, boolean cancelTeleportOnMove, CancelMode cancelMoveMode, boolean blockDamage, boolean cancelTeleportOnDamage, CancelMode cancelDamageMode, boolean staffBypassTime, boolean blockCommands, CancelMode cancelCommandsMode) {
        this.time = time;
        this.blockMove = blockMove;
        this.cancelTeleportOnMove = cancelTeleportOnMove;
        this.cancelMoveMode = cancelMoveMode;
        this.blockDamage = blockDamage;
        this.cancelTeleportOnDamage = cancelTeleportOnDamage;
        this.cancelDamageMode = cancelDamageMode;
        this.staffBypassTime = staffBypassTime;
        this.blockCommands = blockCommands;
        this.cancelCommandsMode = cancelCommandsMode;
    }

    public int getTime() {
        return time;
    }

    public boolean getBlockMove() {
        return blockMove;
    }

    public boolean getCancelTeleportOnMove() {
        return cancelTeleportOnMove;
    }

    public CancelMode getCancelMoveMode() {
        return cancelMoveMode;
    }

    public boolean getBlockDamage() {
        return blockDamage;
    }

    public boolean getCancelTeleportOnDamage() {
        return cancelTeleportOnDamage;
    }

    public CancelMode getCancelDamageMode() {
        return cancelDamageMode;
    }

    public boolean getStaffBypassTime() {
        return staffBypassTime;
    }

    public boolean getBlockCommands() {
        return blockCommands;
    }

    public CancelMode getCancelCommandsMode() {
        return cancelCommandsMode;
    }

}

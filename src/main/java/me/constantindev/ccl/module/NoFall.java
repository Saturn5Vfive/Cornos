package me.constantindev.ccl.module;

import me.constantindev.ccl.Cornos;
import me.constantindev.ccl.etc.base.Module;
import me.constantindev.ccl.etc.ms.MType;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;

import java.util.Objects;

public class NoFall extends Module {
    public NoFall() {
        super("NoFall", "prevents you from dying from falling", MType.MOVEMENT);
    }

    @Override
    public void onExecute() {
        try {
            Objects.requireNonNull(Cornos.minecraft.getNetworkHandler()).sendPacket(new PlayerMoveC2SPacket(true));
        } catch (Exception ignored) {
        }
        super.onExecute();
    }
}

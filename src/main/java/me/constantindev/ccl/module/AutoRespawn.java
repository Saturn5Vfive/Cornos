package me.constantindev.ccl.module;

import me.constantindev.ccl.etc.base.Module;
import net.minecraft.client.MinecraftClient;
import net.minecraft.network.packet.c2s.play.ClientStatusC2SPacket;

import java.util.Objects;

public class AutoRespawn extends Module {
    public AutoRespawn() {
        super("AutoRespawn", "Automatically respawns you upon death");
    }

    @Override
    public void onExecute() {
        assert MinecraftClient.getInstance().player != null;
        if (MinecraftClient.getInstance().player.isDead()) {
            Objects.requireNonNull(MinecraftClient.getInstance().getNetworkHandler()).sendPacket(new ClientStatusC2SPacket(ClientStatusC2SPacket.Mode.PERFORM_RESPAWN));
        }
        super.onExecute();
    }
}
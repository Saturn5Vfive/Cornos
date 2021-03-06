package me.constantindev.ccl.module;

import me.constantindev.ccl.Cornos;
import me.constantindev.ccl.etc.base.Module;
import me.constantindev.ccl.etc.config.MultiOption;
import me.constantindev.ccl.etc.config.Num;
import me.constantindev.ccl.etc.config.Toggleable;
import me.constantindev.ccl.etc.helper.ClientHelper;
import me.constantindev.ccl.etc.helper.RandomHelper;
import net.minecraft.client.network.ClientPlayerEntity;
import me.constantindev.ccl.etc.ms.MType;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.Vec3d;

public class Flight extends Module {
    int counter = 0;

    public Flight() {
        super("Flight", "Allows you to fly", MType.MOVEMENT);
        this.mconf.add(new MultiOption("mode", "vanilla", new String[]{"vanilla", "static", "jetpack"}));
        this.mconf.add(new Toggleable("AntiKick", true));
        this.mconf.add(new Num("speed", 1.0, 30, 0));
    }

    @Override
    public void onExecute() {
        double speed = ((Num) this.mconf.getByName("speed")).getValue();
        ClientPlayerEntity player = Cornos.minecraft.player;
        if (((Toggleable) this.mconf.getByName("AntiKick")).isEnabled()) {
            if (counter > 10) counter = 0;
            counter++;
        } else counter = 0;
        switch (this.mconf.getByName("mode").value) {
            case "vanilla":
                assert Cornos.minecraft.player != null;
                Cornos.minecraft.player.abilities.flying = !(counter > 9);
                break;
            case "static":
                player.abilities.flying = false;
                player.setVelocity(0, 0, 0);
                player.flyingSpeed = (float) speed;
                if(Cornos.minecraft.options.keyJump.isPressed())
                    player.addVelocity(0, speed / 2, 0);
            
                if(Cornos.minecraft.options.keySneak.isPressed())
                    player.addVelocity(0, speed / 2 * -1, 0);
                
                if(counter == 9){
                    player.updatePosition(player.getX(), player.getY() - 0.1, player.getZ()); //yes
                }
                break;
            case "jetpack":
                if (Cornos.minecraft.world == null) return;
                if (Cornos.minecraft.options.keyJump.isPressed()) {
                    assert Cornos.minecraft.player != null;
                    Cornos.minecraft.player.addVelocity(0, speed / 30, 0);
                    Vec3d vp = Cornos.minecraft.player.getPos();
                    for (int i = 0; i < 10; i++) {
                        Cornos.minecraft.world.addParticle(ParticleTypes.SOUL_FIRE_FLAME, vp.x, vp.y, vp.z, RandomHelper.rndD(.25) - .125, RandomHelper.rndD(.25) - .125, RandomHelper.rndD(.25) - .125);
                    }
                }
                break;
            default:
                ClientHelper.sendChat("Invalid flight mode. Please pick one of vanilla, static.");
                this.mconf.getByName("mode").setValue("vanilla");
                break;
        }
        super.onExecute();
    }
}

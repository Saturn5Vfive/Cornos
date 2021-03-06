/*
@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
# Project: Cornos
# File: QuickMove
# Created by constantin at 17:19, Mär 03 2021
PLEASE READ THE COPYRIGHT NOTICE IN THE PROJECT ROOT, IF EXISTENT
@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
*/
package me.constantindev.ccl.module;

import me.constantindev.ccl.Cornos;
import me.constantindev.ccl.etc.base.Module;
import me.constantindev.ccl.etc.ms.MType;
import me.constantindev.ccl.etc.config.ClientConfig;
import me.constantindev.ccl.etc.config.Num;

public class VanillaFly extends Module {
    public VanillaFly() {
        super("VanillaFly","Lets you fly in survival by doubletapping space bar", MType.MOVEMENT);
        Module parent = this;
    }

    @Override
    public void onEnable() {

        super.onEnable();
    }
    int counter = 0;
    @Override
    public void onExecute() {
        Cornos.minecraft.player.abilities.allowFlying = true;
    }

    @Override
    public void onDisable() {
        Cornos.minecraft.player.abilities.allowFlying = false;
    }
}

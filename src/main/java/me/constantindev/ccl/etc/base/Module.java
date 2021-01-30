package me.constantindev.ccl.etc.base;

import me.constantindev.ccl.etc.config.ModuleConfig;

public class Module {
    public final String name;
    public final String description;
    public final ModuleConfig mconf;
    public boolean isEnabled = false;

    public Module(String n, String d) {
        this.name = n;
        this.description = d;
        this.mconf = new ModuleConfig(this);
    }

    public void onExecute() {
    }
}
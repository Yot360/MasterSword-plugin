package com.yot.mastersword;
import com.yot.mastersword.commands.CommandMasterSword;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin{

    @Override
    public void onEnable() {
        System.out.println("[Master Sword] Plugin successfully started!");
        getCommand("mastersword").setExecutor(new CommandMasterSword());
    }

    @Override
    public void onDisable() {
        System.out.println("[Master Sword] Plugin stopped.");
    }
}

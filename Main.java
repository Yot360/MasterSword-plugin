package com.yot.mastersword;

import com.google.gson.Gson;
import com.yot.mastersword.commands.CommandClear;
import com.yot.mastersword.commands.CommandMasterSword;
import com.yot.mastersword.commands.CommandRandomChest;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.*;

public class Main extends JavaPlugin{

    public static File players_json;

    @Override
    public void onEnable() {
        colorStr("[Master Sword] &aPlugin successfully loaded!");
        getCommand("mastersword").setExecutor(new CommandMasterSword());
        getCommand("masterspawn").setExecutor(new CommandRandomChest(this));
        getServer().getPluginManager().registerEvents(new ChestListener(), this);
        getCommand("masterclear").setExecutor(new CommandClear());

        // Create players.json file inside the proper plugin folder
        if(getDataFolder().exists()){
            getDataFolder().mkdir();
        }
        players_json = new File(getDataFolder() + File.separator + "players.json");
        String players_path = players_json.getPath();

        if (!players_json.exists()){
            try {
                String fake_uuid = "[\"0000\"]";
                players_json.createNewFile();
                FileWriter myWriter = new FileWriter(players_path);
                myWriter.write(fake_uuid);
                myWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }
    public void colorStr(String string){
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', string));
    }

    @Override
    public void onDisable() {
        colorStr("[Master Sword] &aPlugin successfully stopped!");
    }
}

package com.yot.mastersword.commands;

import com.yot.mastersword.ChestListener;
import com.yot.mastersword.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandClear implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command cmd, String msg, String[] args) {
        if(cmd.getName().equals("masterclear")) {
            Main.players_json.delete();
            commandSender.sendMessage("Names cleared!");
        }
        return false;
    }
}

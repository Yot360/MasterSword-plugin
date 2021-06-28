package com.yot.mastersword.commands;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class CommandMasterSword implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command cmd, String msg, String[] args) {
        if(commandSender instanceof Player){
            Player player = (Player)commandSender;

            if(cmd.getName().equalsIgnoreCase("mastersword")){
                //shows master sword message
                Bukkit.broadcastMessage("§3§lThe master sword has chosen its new hero.");
                //divides health by 4
                player.setHealth(player.getHealth()/4);
                //summons lightning
                player.getWorld().strikeLightningEffect(player.getLocation());
                //setup and gives the sword
                ItemStack customsword = new ItemStack(Material.DIAMOND_SWORD, 1);
                ItemMeta customM = customsword.getItemMeta();
                customM.setDisplayName("Master sword");
                customM.setLore(Arrays.asList("The legendary","sword that","seals the darkness"));
                customM.addEnchant(Enchantment.DAMAGE_ALL, 400, true);
                customM.addEnchant(Enchantment.DURABILITY, 10, true);
                customM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                customsword.setItemMeta(customM);
                player.getInventory().setItemInMainHand(customsword);

                return true;
            }
        }

        return false;
    }
}

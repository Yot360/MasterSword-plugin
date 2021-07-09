package com.yot.mastersword.commands;

import com.yot.mastersword.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Chest;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

public class CommandRandomChest implements CommandExecutor{
    public CommandRandomChest(Main main) {
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command cmd, String msg, String[] args) {
        Player player = (Player)commandSender;
        if(cmd.getName().equalsIgnoreCase("masterspawn")){

            //set random coordinates
            Random r = new Random();
            double x = 100 + r.nextInt(5000);
            double y = r.nextInt(100);
            double z = r.nextInt(50000);

            //Makes the custom sword
            ItemStack customsword = new ItemStack(Material.DIAMOND_SWORD, 1);
            ItemMeta customM = customsword.getItemMeta();
            customM.setDisplayName("Master sword");
            customM.setLore(Arrays.asList("The legendary","sword that","seals the darkness"));
            customM.addEnchant(Enchantment.DAMAGE_ALL, 400, true);
            customM.addEnchant(Enchantment.DURABILITY, 10, true);
            customM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            customsword.setItemMeta(customM);

            //spawn the chest and adds it to it
            Location spawnChest = new Location(player.getWorld(), x, y, z);
            System.out.println(spawnChest);
            spawnChest.getBlock().setType(Material.CHEST);
            Chest chest = (Chest) spawnChest.getBlock().getState();
            Inventory chestMenu = chest.getInventory();
            chest.setCustomName("Master Sword Chest");
            chestMenu.setItem(13, customsword);

            //tells where it spawned approximately
            Bukkit.broadcastMessage("§3Chest containing the Master Sword spawned in x: ???, y: "+y+" z: "+z);
            System.out.println("Chest spawned in "+x +y +z);
            return true;
        }
        return false;
    }
}

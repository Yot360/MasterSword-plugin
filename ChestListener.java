package com.yot.mastersword;

import com.google.gson.Gson;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import org.json.simple.parser.ParseException;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class ChestListener implements Listener {

    public static List<UUID> players = null;

    @EventHandler
    public void clickChest(PlayerInteractEvent e) throws ParseException, IOException{
        Player p = e.getPlayer();
        String players_path = Main.players_json.getPath();

        //check if serialized
        if (players == null) {
            //checks the JSON
            String file_json = players_path;
            Gson gson = new Gson();
            Reader reader = null;
            try {
                reader = Files.newBufferedReader(Paths.get(file_json));
            } catch (IOException f) {
                f.printStackTrace();
            }
            assert reader != null;
            players = gson.fromJson(reader, List.class);
            try {
                reader.close();
            } catch (IOException f) {
                f.printStackTrace();
            }
            //System.out.println(players);
        }

        //Makes the custom sword
        ItemStack customsword = new ItemStack(Material.DIAMOND_SWORD, 1);
        ItemMeta customM = customsword.getItemMeta();
        assert customM != null;
        customM.setDisplayName("Master sword");
        customM.setLore(Arrays.asList("The legendary", "sword that", "seals the darkness"));
        customM.addEnchant(Enchantment.DAMAGE_ALL, 400, true);
        customM.addEnchant(Enchantment.DURABILITY, 10, true);
        customM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        customsword.setItemMeta(customM);
        UUID uuid = p.getUniqueId();
        String uuidStr = uuid.toString();
        if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (!players.contains(uuidStr)) {
                Block b = e.getClickedBlock();

                //check if its a chest
                assert b != null;
                if (b.getType().equals(Material.CHEST)) {
                    Chest chest = (Chest) b.getState();
                    //checks if chest has the custom name : Master Sword Chest
                    if (chest.getInventory().contains(customsword)) {
                        Bukkit.broadcastMessage("§3§lThe master sword has chosen its new hero... " + p.getName());
                        //divides health by 4
                        p.setHealth(p.getHealth() / 4);
                        //summons lightning
                        p.getWorld().strikeLightningEffect(p.getLocation());

                        //add UUID to List
                        String file_json = players_path;
                        Gson gson = new Gson();
                        players.add(uuid);
                        System.out.println("Player " + p.getName() + " has been added to mastersword list! with the uuid: " + p.getUniqueId());
                        try (Writer writer = new FileWriter(file_json)) {
                            gson.toJson(players, writer);
                        }
                        players = null;
                    }
                }
            }
        }
    }
}

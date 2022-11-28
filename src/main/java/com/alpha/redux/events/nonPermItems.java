package com.alpha.redux.events;

import com.alpha.redux.apis.Sounds;
import com.alpha.redux.items.enchants;
import com.alpha.redux.items.itemManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.alpha.redux.DeathHandler.MysticUtils.MysticLivesHandler;
import static com.alpha.redux.DeathHandler.MysticUtils.registerCommonItems;
import static com.alpha.redux.apis.advancedInventory.ItemMaker;
import static com.alpha.redux.playerdata.streaks.*;

public class nonPermItems {

    public static void ClearAndCheck(Player player){
        try {
            List<ItemStack> hotbar = new ArrayList<>();

            hotbar.add(player.getInventory().getItem(0)); // 1
            hotbar.add(player.getInventory().getItem(1)); // 2
            hotbar.add(player.getInventory().getItem(2)); // 3
            hotbar.add(player.getInventory().getItem(3)); // 4
            hotbar.add(player.getInventory().getItem(4)); // 5
            hotbar.add(player.getInventory().getItem(5)); // 6
            hotbar.add(player.getInventory().getItem(6)); // 7
            hotbar.add(player.getInventory().getItem(7)); // 8
            hotbar.add(player.getInventory().getItem(8)); // 9

            for (ItemStack item : hotbar){
                if(item != null &&
                item.getItemMeta() != null
                && item.getItemMeta().getLore() != null &&
                item.getItemMeta().getLore().equals(itemManager.feather.getItemMeta().getLore())){
                    player.getInventory().removeItem(itemManager.feather);
                    Sounds.FUNKY_FEATHER.play(player);
                    player.sendMessage(ChatColor.AQUA + "Your funky feather just saved your items!");
                    return;
                }
            }

                hasStreak(String.valueOf(player.getUniqueId()));

                try{
                    if(player.getInventory().getLeggings().getType().equals(Material.LEATHER_LEGGINGS)){
                        MysticLivesHandler(player, player.getInventory().getLeggings());
                    }
                } catch (Exception e) {

                }

                registerCommonItems(player);

                try {
                    if (player.getInventory().contains(ItemMaker(Material.OBSIDIAN, "NULL", "NULL", 1, false))){
                        player.getInventory().remove(ItemMaker(Material.OBSIDIAN, "NULL", "NULL", 1, false));
                    }
                }catch (Exception e){

                }

                if(player.getInventory().contains(enchants.firstaidempty)){
                    player.getInventory().remove(enchants.firstaidempty);
                }

                if(player.getInventory().contains(enchants.firstaidfull)){
                    player.getInventory().remove(enchants.firstaidfull);
                }

                try {
                    if (player.getInventory().contains(ItemMaker(Material.DIAMOND_SWORD, "NULL", "NULL", 1, false))) {
                        player.getInventory().remove(ItemMaker(Material.DIAMOND_SWORD, "NULL", "NULL", 1, false));
                    }
                }catch (Exception e){

                }

                try {
                    if (player.getInventory().contains(ItemMaker(Material.DIAMOND_SPADE, ChatColor.AQUA + "Combat Spade", ChatColor.GRAY + "Deals " + ChatColor.BLUE +
                            "+1 damage per\n" + ChatColor.AQUA + "diamond piece " + ChatColor.GRAY + "on enemy." + "\n\n" +
                            ChatColor.BLUE + "+7 Attack Damage", 1, false))) {
                        player.getInventory().remove(ItemMaker(Material.DIAMOND_SPADE, ChatColor.AQUA + "Combat Spade", ChatColor.GRAY + "Deals " + ChatColor.BLUE +
                                "+1 damage per\n" + ChatColor.AQUA + "diamond piece " + ChatColor.GRAY + "on enemy." + "\n\n" +
                                ChatColor.BLUE + "+7 Attack Damage", 1, false));
                    }
                }catch (Exception e){

                }

                try {
                    if (player.getInventory().getLeggings().equals(itemManager.DiamondLeggings)) {
                        player.getInventory().setLeggings(null);
                    }
                }catch (Exception e){

                }

            try {
                if (player.getInventory().getChestplate().equals(itemManager.arch)) {
                    player.getInventory().setChestplate(null);
                }
            }catch (Exception e){

            }

            try {
                if (player.getInventory().getHelmet().equals(itemManager.goldHelm)) {
                    player.getInventory().setHelmet(null);
                }
            }catch (Exception e){

            }

                try {
                    if ((player.getInventory().getChestplate().equals(itemManager.DiamondChestplate))) {
                        player.getInventory().setChestplate(null);
                    }
                }catch (Exception e){

                }


                try {
                    if ((player.getInventory().getBoots().equals(itemManager.DiamondBoots))) {
                        player.getInventory().setBoots(null);
                    }
                }catch (Exception e){

                }

                try {
                    if ((player.getInventory().getHelmet().equals(itemManager.DiamondHelmet))) {
                        player.getInventory().setHelmet(null);
                    }
                }catch (Exception e){

                }


                try {
                    if (player.getInventory().contains(itemManager.DiamondBoots)) {
                        player.getInventory().remove(itemManager.DiamondBoots);
                    }
                }catch (Exception e){

                }

                try {
                    if (player.getInventory().contains(itemManager.DiamondChestplate)) {
                        player.getInventory().remove(itemManager.DiamondChestplate);
                    }
                }catch (Exception e){

                }

            try {
                if (player.getInventory().contains(itemManager.arch)) {
                    player.getInventory().remove(itemManager.arch);
                }
            }catch (Exception e){

            }

            try {
                if (player.getInventory().contains(itemManager.goldHelm)) {
                    player.getInventory().remove(itemManager.goldHelm);
                }
            }catch (Exception e){

            }

                try {
                    if (player.getInventory().contains(itemManager.DiamondSword)) {
                        player.getInventory().remove(itemManager.DiamondSword);
                    }
                }catch (Exception e){

                }

                try {
                    if (player.getInventory().contains(itemManager.DiamondBoots)) {
                        player.getInventory().remove(itemManager.DiamondBoots);
                    }
                }catch (Exception e){

                }

                try {
                    if (player.getInventory().contains(itemManager.DiamondLeggings)) {
                        player.getInventory().remove(itemManager.DiamondLeggings);
                    }
                }catch (Exception e){

                }

                try {
                    if (player.getInventory().contains(itemManager.DiamondHelmet)) {
                        player.getInventory().remove(itemManager.DiamondHelmet);
                    }
                }catch (Exception e){

                }

                try {
                    if (player.getInventory().contains(ItemMaker(Material.DIAMOND_BOOTS, "NULL", "NULL", 1, false))) {
                        player.getInventory().remove(ItemMaker(Material.DIAMOND_BOOTS, "NULL", "NULL", 1, false));
                    }

                }catch (Exception e){

                }

                try {
                    if (player.getInventory().contains(ItemMaker(Material.DIAMOND_LEGGINGS, "NULL", "NULL", 1, false))) {
                        player.getInventory().remove(ItemMaker(Material.DIAMOND_LEGGINGS, "NULL", "NULL", 1, false));
                    }
                }catch (Exception e){

                }

                try {
                    if (player.getInventory().contains(ItemMaker(Material.DIAMOND_CHESTPLATE, "NULL", "NULL", 1, false))) {
                        player.getInventory().remove(ItemMaker(Material.DIAMOND_CHESTPLATE, "NULL", "NULL", 1, false));
                    }
                }catch (Exception e){

                }

                try {
                    if (player.getInventory().getLeggings().equals(ItemMaker(Material.DIAMOND_LEGGINGS, "NULL", "NULL", 1, false))) {
                        player.getInventory().setLeggings(null);
                    }
                }catch (Exception e){

                }

                try {
                    if (player.getInventory().getChestplate().equals(ItemMaker(Material.DIAMOND_CHESTPLATE, "NULL", "NULL", 1, false))) {
                        player.getInventory().setChestplate(null);
                    }
                }catch (Exception e){

                }

                try {
                    if (player.getInventory().getBoots().equals(ItemMaker(Material.DIAMOND_BOOTS, "NULL", "NULL", 1, false))) {
                        player.getInventory().setBoots(null);
                    }

                }catch (Exception e){

                }

        }catch(Exception e){
                return;
            }


        }

    public static void ClearRegular(Player player){
        try {
            List<ItemStack> hotbar = new ArrayList<>();

            hotbar.add(player.getInventory().getItem(0)); // 1
            hotbar.add(player.getInventory().getItem(1)); // 2
            hotbar.add(player.getInventory().getItem(2)); // 3
            hotbar.add(player.getInventory().getItem(3)); // 4
            hotbar.add(player.getInventory().getItem(4)); // 5
            hotbar.add(player.getInventory().getItem(5)); // 6
            hotbar.add(player.getInventory().getItem(6)); // 7
            hotbar.add(player.getInventory().getItem(7)); // 8
            hotbar.add(player.getInventory().getItem(8)); // 9

            for (ItemStack item : hotbar){
                if(item != null &&
                        item.getItemMeta() != null
                        && item.getItemMeta().getLore() != null &&
                        item.getItemMeta().getLore().equals(itemManager.feather.getItemMeta().getLore())){
                    player.getInventory().removeItem(itemManager.feather);
                    Sounds.FUNKY_FEATHER.play(player);
                    player.sendMessage(ChatColor.AQUA + "Your funky feather just saved your items!");
                    return;
                }
            }

            hasStreak(String.valueOf(player.getUniqueId()));

            try {
                if (player.getInventory().contains(ItemMaker(Material.OBSIDIAN, "NULL", "NULL", 1, false))){
                    player.getInventory().remove(ItemMaker(Material.OBSIDIAN, "NULL", "NULL", 1, false));
                }
            }catch (Exception e){

            }

            if(player.getInventory().contains(enchants.firstaidempty)){
                player.getInventory().remove(enchants.firstaidempty);
            }

            if(player.getInventory().contains(enchants.firstaidfull)){
                player.getInventory().remove(enchants.firstaidfull);
            }

            try {
                if (player.getInventory().contains(ItemMaker(Material.DIAMOND_SWORD, "NULL", "NULL", 1, false))) {
                    player.getInventory().remove(ItemMaker(Material.DIAMOND_SWORD, "NULL", "NULL", 1, false));
                }
            }catch (Exception e){

            }

            try {
                if (player.getInventory().contains(ItemMaker(Material.DIAMOND_SPADE, ChatColor.AQUA + "Combat Spade", ChatColor.GRAY + "Deals " + ChatColor.BLUE +
                        "+1 damage per\n" + ChatColor.AQUA + "diamond piece " + ChatColor.GRAY + "on enemy." + "\n\n" +
                        ChatColor.BLUE + "+7 Attack Damage", 1, false))) {
                    player.getInventory().remove(ItemMaker(Material.DIAMOND_SPADE, ChatColor.AQUA + "Combat Spade", ChatColor.GRAY + "Deals " + ChatColor.BLUE +
                            "+1 damage per\n" + ChatColor.AQUA + "diamond piece " + ChatColor.GRAY + "on enemy." + "\n\n" +
                            ChatColor.BLUE + "+7 Attack Damage", 1, false));
                }
            }catch (Exception e){

            }

            try {
                if (player.getInventory().getLeggings().equals(itemManager.DiamondLeggings)) {
                    player.getInventory().setLeggings(null);
                }
            }catch (Exception e){

            }

            try {
                if ((player.getInventory().getChestplate().equals(itemManager.DiamondChestplate))) {
                    player.getInventory().setChestplate(null);
                }
            }catch (Exception e){

            }


            try {
                if ((player.getInventory().getBoots().equals(itemManager.DiamondBoots))) {
                    player.getInventory().setBoots(null);
                }
            }catch (Exception e){

            }

            try {
                if ((player.getInventory().getHelmet().equals(itemManager.DiamondHelmet))) {
                    player.getInventory().setHelmet(null);
                }
            }catch (Exception e){

            }


            try {
                if (player.getInventory().contains(itemManager.DiamondBoots)) {
                    player.getInventory().remove(itemManager.DiamondBoots);
                }
            }catch (Exception e){

            }

            try {
                if (player.getInventory().contains(itemManager.DiamondChestplate)) {
                    player.getInventory().remove(itemManager.DiamondChestplate);
                }
            }catch (Exception e){

            }

            try {
                if (player.getInventory().contains(itemManager.DiamondSword)) {
                    player.getInventory().remove(itemManager.DiamondSword);
                }
            }catch (Exception e){

            }

            try {
                if (player.getInventory().contains(itemManager.DiamondBoots)) {
                    player.getInventory().remove(itemManager.DiamondBoots);
                }
            }catch (Exception e){

            }

            try {
                if (player.getInventory().contains(itemManager.DiamondLeggings)) {
                    player.getInventory().remove(itemManager.DiamondLeggings);
                }
            }catch (Exception e){

            }

            try {
                if (player.getInventory().contains(itemManager.DiamondHelmet)) {
                    player.getInventory().remove(itemManager.DiamondHelmet);
                }
            }catch (Exception e){

            }

            try {
                if (player.getInventory().contains(ItemMaker(Material.DIAMOND_BOOTS, "NULL", "NULL", 1, false))) {
                    player.getInventory().remove(ItemMaker(Material.DIAMOND_BOOTS, "NULL", "NULL", 1, false));
                }

            }catch (Exception e){

            }

            try {
                if (player.getInventory().contains(ItemMaker(Material.DIAMOND_LEGGINGS, "NULL", "NULL", 1, false))) {
                    player.getInventory().remove(ItemMaker(Material.DIAMOND_LEGGINGS, "NULL", "NULL", 1, false));
                }
            }catch (Exception e){

            }

            try {
                if (player.getInventory().contains(ItemMaker(Material.DIAMOND_CHESTPLATE, "NULL", "NULL", 1, false))) {
                    player.getInventory().remove(ItemMaker(Material.DIAMOND_CHESTPLATE, "NULL", "NULL", 1, false));
                }
            }catch (Exception e){

            }

            try {
                if (player.getInventory().getLeggings().equals(ItemMaker(Material.DIAMOND_LEGGINGS, "NULL", "NULL", 1, false))) {
                    player.getInventory().setLeggings(null);
                }
            }catch (Exception e){

            }

            try {
                if (player.getInventory().getChestplate().equals(ItemMaker(Material.DIAMOND_CHESTPLATE, "NULL", "NULL", 1, false))) {
                    player.getInventory().setChestplate(null);
                }
            }catch (Exception e){

            }

            try {
                if (player.getInventory().getBoots().equals(ItemMaker(Material.DIAMOND_BOOTS, "NULL", "NULL", 1, false))) {
                    player.getInventory().setBoots(null);
                }

            }catch (Exception e){

            }

        }catch(Exception e){
            return;
        }


    }
    }

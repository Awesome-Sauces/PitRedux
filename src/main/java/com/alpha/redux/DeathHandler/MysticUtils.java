package com.alpha.redux.DeathHandler;


import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

import static com.alpha.redux.apis.chatManager.rank.colorCode;

public class MysticUtils {
    public static void MysticLivesHandler(Player player, ItemStack item){
        String lives = item.getItemMeta().getLore().get(0);

        if(ChatColor.stripColor(lives).contains("Lives: 1/5")){
            player.getInventory().removeItem(item);
        }

        try{
            if(ChatColor.stripColor(player.getInventory().getLeggings().getItemMeta().getLore().get(0)).contains("Lives: 1/5")){
                player.getInventory().setLeggings(null);
            }

        } catch (Exception e) {

        }

        if(ChatColor.stripColor(lives).contains("Lives: 0/5")){
            player.getInventory().removeItem(item);
        }

        try{
            if(ChatColor.stripColor(player.getInventory().getLeggings().getItemMeta().getLore().get(0)).contains("Lives: 0/5")){
                player.getInventory().setLeggings(null);
            }

        } catch (Exception e) {

        }

        ItemMeta meta = item.getItemMeta();
        meta.setLore(MysticLivesCounter(meta.getLore()));
        item.setItemMeta(meta);

    }

    public static List<String> MysticLivesCounter(List<String> lore){
        String lives = lore.get(0);

        if(ChatColor.stripColor(lives).contains("Lives: 5/5")){
            lore.set(0, lore.get(0).replaceAll(colorCode("&7Lives: &a5&7/5"), colorCode("&7Lives: &a4&7/5")));
            return lore;
        }else if(ChatColor.stripColor(lives).contains("Lives: 4/5")){
            lore.set(0, lore.get(0).replaceAll(colorCode("&7Lives: &a4&7/5"), colorCode("&7Lives: &a3&7/5")));
            return lore;
        }else if(ChatColor.stripColor(lives).contains("Lives: 3/5")){
            lore.set(0, lore.get(0).replaceAll(colorCode("&7Lives: &a3&7/5"), colorCode("&7Lives: &a2&7/5")));
            return lore;
        }else if(ChatColor.stripColor(lives).contains("Lives: 2/5")){
            lore.set(0, lore.get(0).replaceAll(colorCode("&7Lives: &a2&7/5"), colorCode("&7Lives: &a1&7/5")));
            return lore;
        }else if(ChatColor.stripColor(lives).contains("Lives: 1/5")){
            lore.set(0, lore.get(0).replaceAll(colorCode("&7Lives: &a1&7/5"), colorCode("&7Lives: &a0&7/5")));
            return lore;
        }else{
            return lore;
        }
    }

    public static List<String> MysticRepairs(List<String> lore){
        String lives = lore.get(0);

        if(ChatColor.stripColor(lives).contains("Lives: 5/5")){
            lore.set(0, lore.get(0).replaceAll(colorCode("&7Lives: &a5&7/5"), colorCode("&7Lives: &a6&7/5")));
            return lore;
        }else if(ChatColor.stripColor(lives).contains("Lives: 4/5")){
            lore.set(0, lore.get(0).replaceAll(colorCode("&7Lives: &a4&7/5"), colorCode("&7Lives: &a5&7/5")));
            return lore;
        }else if(ChatColor.stripColor(lives).contains("Lives: 3/5")){
            lore.set(0, lore.get(0).replaceAll(colorCode("&7Lives: &a3&7/5"), colorCode("&7Lives: &a4&7/5")));
            return lore;
        }else if(ChatColor.stripColor(lives).contains("Lives: 2/5")){
            lore.set(0, lore.get(0).replaceAll(colorCode("&7Lives: &a2&7/5"), colorCode("&7Lives: &a3&7/5")));
            return lore;
        }else if(ChatColor.stripColor(lives).contains("Lives: 1/5")){
            lore.set(0, lore.get(0).replaceAll(colorCode("&7Lives: &a1&7/5"), colorCode("&7Lives: &a2&7/5")));
            return lore;
        }else{
            return lore;
        }
    }


    public static void registerCommonItems(Player p){
        for(ItemStack i : p.getInventory().getContents()){
            try{
                if(i.getType().equals(Material.GOLD_SWORD)){
                    if(ChatColor.stripColor(i.getItemMeta().getDisplayName()).contains("Tier")){
                        MysticLivesHandler(p, i);
                    }
                }
            }catch (Exception e){

            }

            try{
                if(i.getType().equals(Material.LEATHER_LEGGINGS)){
                    if(ChatColor.stripColor(i.getItemMeta().getDisplayName()).contains("Tier")){
                        MysticLivesHandler(p, i);
                    }
                }
            } catch (Exception e) {

            }

        }

    }
}

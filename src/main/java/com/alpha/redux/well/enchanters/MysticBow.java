package com.alpha.redux.well.enchanters;

import com.alpha.redux.events.boards;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import static com.alpha.redux.apis.chatManager.rank.colorCode;
import static com.alpha.redux.playerdata.economy.getEconomy;
import static com.alpha.redux.playerdata.economy.removeEconomy;
import static com.alpha.redux.well.mysticWell.*;

public class MysticBow {

    private static boolean removeGold(Player player, String uuid, int amount){
        if(getEconomy(uuid) >= amount){
            removeEconomy(uuid, amount);
            boards.CreateScore(player);
            return true;
        }else{
            player.sendMessage(colorCode("&l&cERROR! &7You need &6" + (amount-getEconomy(uuid)) + "g &7to afford this!"));
            return false;
        }
    }

    public static void clickBow(InventoryClickEvent event){
        event.setCancelled(true);

        String uuid = String.valueOf(event.getWhoClicked().getUniqueId());
        Player player = (Player) event.getWhoClicked();
        ItemStack items = event.getClickedInventory().getItem(20);

        if (items.getItemMeta().getDisplayName().contains("Tier III")){
            player.sendMessage(ChatColor.RED + "This sword is already max tier!");
            return;
        } else if (items.getItemMeta().getDisplayName().contains("Tier II") && removeGold(player, uuid, 8000)) {
            event.getClickedInventory().setItem(20, createBowII(event.getClickedInventory().getItem(20), player));
        }else if (items.getItemMeta().getDisplayName().contains("Tier I") && removeGold(player, uuid, 4000)) {
            event.getClickedInventory().setItem(20, createBowI(event.getClickedInventory().getItem(20), player));
        } else if (items.getItemMeta().getDisplayName().contains("Mystic Bow") && removeGold(player, uuid, 1000)) {
            event.getClickedInventory().setItem(20, createBow(player));
        }


    }

}

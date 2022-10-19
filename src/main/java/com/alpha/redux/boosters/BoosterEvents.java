package com.alpha.redux.boosters;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import static com.alpha.redux.apis.chatManager.rank.colorCode;

public class BoosterEvents implements Listener {
    @EventHandler
    public void clickBoosterInventory(InventoryClickEvent event){
        if(!event.getClickedInventory().getTitle().equalsIgnoreCase(ChatColor.GRAY+"Boosters")) return;

        Player player = (Player) event.getWhoClicked();

        event.setCancelled(true);

        String clicked = "";

        if(event.getCurrentItem().getType().equals(Material.EXP_BOTTLE)){
            clicked="XP";
        }else if(event.getCurrentItem().getType().equals(Material.GOLD_INGOT)){
            clicked="GOLD";
        }else if(event.getCurrentItem().getType().equals(Material.IRON_SWORD)){
            clicked="BOT";
        }


        boolean outcome = Booster.activateBooster(player, clicked);

        if(outcome){
            player.sendMessage(colorCode("&c&lERROR! &7this booster is already activated!"));
        }else{
            player.sendMessage(colorCode("&a&lSUCCESS! &7this booster has been activated!"));
        }

        player.sendMessage(colorCode("&7Feel free to buy boosters at &bhttps://betterpit.tebex.io/category/boosters"));


        player.openInventory(Booster.getBoosterGUI(player));
    }
}

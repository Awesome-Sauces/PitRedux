package com.alpha.redux.commands.repairs;

import com.alpha.redux.items.enchants;
import com.alpha.redux.well.EnchantingMechanics;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import static com.alpha.redux.DeathHandler.MysticUtils.MysticRepairs;
import static com.alpha.redux.gems.gemMain.cancelButton;
import static com.alpha.redux.gems.gemMain.confirmButton;
import static com.alpha.redux.well.EnchantingMechanics.getUpgradeableEnchants;

public class ClickHandler implements Listener {

    public static HashMap<String, ItemStack> repairItem = new HashMap<>();

    @EventHandler
    public static void repairLife(InventoryClickEvent event){
        if(event == null) return;
        if(event.getClickedInventory().getTitle() == null) return;
        if(event.getClickedInventory().getTitle().equals(ChatColor.GRAY + "Mystic Repair")) {

            event.setCancelled(true);

            if(event.getCurrentItem().equals(confirmButton)){
                if(event.getClickedInventory().getItem(13) != null && event.getClickedInventory().getItem(13).getItemMeta() != null && event.getClickedInventory().getItem(13).getItemMeta().getLore() != null){

                    if(event.getClickedInventory().getItem(13).getType().equals(Material.LEATHER_LEGGINGS) || event.getClickedInventory().getItem(13).getType().equals(Material.GOLD_SWORD) || event.getClickedInventory().getItem(13).getType().equals(Material.BOW)){

                        Player player = (Player) event.getWhoClicked();

                        if(event.getWhoClicked().getInventory().containsAtLeast(enchants.vile, 1)){

                            ItemMeta meta = event.getClickedInventory().getItem(13).getItemMeta();

                            String lives = meta.getLore().get(0);

                            if(ChatColor.stripColor(lives).contains("Lives: 5/5")){
                                player.sendMessage(ChatColor.RED + "This mystic has the max lives!");
                                return;
                            }



                            meta.setLore(MysticRepairs(meta.getLore()));

                            event.getClickedInventory().getItem(13).setItemMeta(meta);
                            player.getInventory().removeItem(enchants.vile);
                            player.sendMessage(ChatColor.GREEN + "+1" + ChatColor.DARK_GRAY + " Mystic Life");
                        }else{
                            player.sendMessage(ChatColor.RED + "You don't have any vile!");
                        }
                        return;
                    }else{
                        event.getWhoClicked().sendMessage(ChatColor.RED + "Please put a mystic in the repairs menu!");
                    }

                }
            }else if(event.getCurrentItem().equals(cancelButton)) {
                event.getWhoClicked().closeInventory();
            }

            event.setCancelled(true);
        }
    }

    @EventHandler
    public static void closeInv(InventoryCloseEvent event){
        if(event.getInventory().getTitle().equals(ChatColor.GRAY + "Mystic Repair")){
            if(event.getInventory().getItem(13) != null) event.getPlayer().getInventory().addItem(event.getInventory().getItem(13));
        }
    }

}

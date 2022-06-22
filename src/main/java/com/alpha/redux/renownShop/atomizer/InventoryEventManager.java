package com.alpha.redux.renownShop.atomizer;

import com.alpha.redux.apis.Sounds;
import com.alpha.redux.entityHandlers.ReduxPlayer;
import com.alpha.redux.items.enchants;
import com.alpha.redux.items.itemManager;
import com.alpha.redux.playerdata.economy;
import com.alpha.redux.renownShop.RenownStorage;
import net.citizensnpcs.api.CitizensAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

import static com.alpha.redux.entityHandlers.ReduxPlayerHandler.playerExists;
import static com.alpha.redux.renownShop.RenownStorage.getToken;
import static com.alpha.redux.renownShop.atomizer.Atomizer.inventoryConstructor;

public class InventoryEventManager implements Listener {

    @EventHandler
    public static void openMenuClick(PlayerInteractEvent event){
        if(event.getPlayer().getInventory().getItemInHand() == null) return;
        if(event.getPlayer().getInventory().getItemInHand().getItemMeta() == null) return;
        if(event.getPlayer().getInventory().getItemInHand().getItemMeta().getDisplayName() != null & Objects.equals(event.getPlayer().getInventory().getItemInHand().getItemMeta().getDisplayName(), ChatColor.LIGHT_PURPLE + "Nuclear Atomizer")) {
            if(playerExists(event.getPlayer()).getPlayerPrestige() >= 30) event.getPlayer().openInventory(inventoryConstructor(event.getPlayer()));
            else event.getPlayer().sendMessage(ChatColor.RED + "You aren't high enough prestige to use this!");
        }
    }

    @EventHandler
    public void heister(InventoryClickEvent event){
        if(!event.getClickedInventory().getTitle().equals(ChatColor.LIGHT_PURPLE + "Heist Master")) return;

        event.setCancelled(true);

        ItemStack token = getToken();
        Player player = (Player) event.getWhoClicked();
        ReduxPlayer reduxPlayer = playerExists(player);
        boolean emptyInv = false;

        for(ItemStack is : player.getInventory().getContents())
            if (is == null) {
                emptyInv = true;
                break;
            }

        if(!emptyInv){
            player.sendMessage(ChatColor.RED + "Come back when you have a free inventory slot!");
            player.closeInventory();
            return;
        }

        if(reduxPlayer.getPlayerPrestige() < 35){
            player.sendMessage(ChatColor.RED + "Come back when you are prestige XXXV!");
            player.closeInventory();
            return;
        }

        if(event.getCurrentItem().equals(Atomizer.heistOne)){
            if (player.getInventory().containsAtLeast(token, 576) && reduxPlayer.getPlayerGold() >= 32000000){
                for (int i = 0; i < 576; i++) {
                    player.getInventory().removeItem(token);
                }

                reduxPlayer.setPlayerGold((int) (reduxPlayer.getPlayerGold() - 32000000));

                player.sendMessage(ChatColor.GREEN + "Purchased successfully!");
                Sounds.PRESTIGE.play(player);
                player.getInventory().addItem(itemManager.heistMasterI);

            }else{
                player.sendMessage(ChatColor.RED + "You can't afford this!");
                player.closeInventory();
            }
        }else if(event.getCurrentItem().equals(Atomizer.heistTwo)){
            if (player.getInventory().containsAtLeast(token, 1152) && reduxPlayer.getPlayerGold() >= 64000000){
                for (int i = 0; i < 1152; i++) {
                    player.getInventory().removeItem(token);
                }

                reduxPlayer.setPlayerGold((int) (reduxPlayer.getPlayerGold() - 64000000));

                player.sendMessage(ChatColor.GREEN + "Purchased successfully!");
                Sounds.PRESTIGE.play(player);
                player.getInventory().addItem(itemManager.heistMasterII);

            }else{
                player.sendMessage(ChatColor.RED + "You can't afford this!");
                player.closeInventory();
            }
        }else if(event.getCurrentItem().equals(Atomizer.heistThree)){
            if (player.getInventory().containsAtLeast(token, 1728) && reduxPlayer.getPlayerGold() >= 96000000){
                for (int i = 0; i < 1728; i++) {
                    player.getInventory().removeItem(token);
                }

                reduxPlayer.setPlayerGold((int) (reduxPlayer.getPlayerGold() - 96000000));

                player.sendMessage(ChatColor.GREEN + "Purchased successfully!");
                Sounds.PRESTIGE.play(player);
                player.getInventory().addItem(itemManager.heistMasterIII);

            }else{
                player.sendMessage(ChatColor.RED + "You can't afford this!");
                player.closeInventory();
            }
        }
    }

    @EventHandler
    public void boosterBeacon(InventoryClickEvent event){
        if(!event.getClickedInventory().getTitle().equals(ChatColor.LIGHT_PURPLE + "Booster Atoms")) return;

        event.setCancelled(true);

        ItemStack token = getToken();
        Player player = (Player) event.getWhoClicked();
        ReduxPlayer reduxPlayer = playerExists(player);
        boolean emptyInv = false;

        for(ItemStack is : player.getInventory().getContents())
            if (is == null) {
                emptyInv = true;
                break;
            }

        if(!emptyInv){
            player.sendMessage(ChatColor.RED + "Come back when you have a free inventory slot!");
            player.closeInventory();
            return;
        }


        if(event.getCurrentItem().equals(Atomizer.xpBoost)){
            if (player.getInventory().containsAtLeast(token, 192)){

                if(reduxPlayer.getPlayerXpBooster() >= 2){
                    player.sendMessage(ChatColor.RED + "You have this buff active already!");
                    player.closeInventory();
                    return;
                }

                for (int i = 0; i < 192; i++) {
                    player.getInventory().removeItem(token);
                }

                player.sendMessage(ChatColor.GREEN + "Purchased successfully!");
                reduxPlayer.setPlayerXpBooster(2);
                Sounds.PRESTIGE.play(player);

                Bukkit.getScheduler().scheduleSyncDelayedTask(economy.getPlugin(), new Runnable() {
                    @Override
                    public void run() {

                        reduxPlayer.setPlayerXpBooster(1);
                        player.sendMessage(ChatColor.GRAY + "Your Experience Atom decayed and the effects have worn off!");

                    }
                }, 12000L);


            }else{
                player.sendMessage(ChatColor.RED + "You can't afford this!");
                player.closeInventory();
            }
        }else if(event.getCurrentItem().equals(Atomizer.goldBoost)){
            if (player.getInventory().containsAtLeast(token, 128)){

                if(reduxPlayer.getPlayerGoldBooster() >= 2){
                    player.sendMessage(ChatColor.RED + "You have this buff active already!");
                    player.closeInventory();
                    return;
                }

                for (int i = 0; i < 128; i++) {
                    player.getInventory().removeItem(token);
                }

                player.sendMessage(ChatColor.GREEN + "Purchased successfully!");
                reduxPlayer.setPlayerGoldBooster(2);
                Sounds.PRESTIGE.play(player);

                Bukkit.getScheduler().scheduleSyncDelayedTask(economy.getPlugin(), new Runnable() {
                    @Override
                    public void run() {

                        reduxPlayer.setPlayerGoldBooster(1);
                        player.sendMessage(ChatColor.GRAY + "Your Gold Atom decayed and the effects have worn off!");

                    }
                }, 12000L);


            }else{
                player.sendMessage(ChatColor.RED + "You can't afford this!");
                player.closeInventory();
            }
        }



    }

    @EventHandler
    public void main(InventoryClickEvent event){
        if(!event.getClickedInventory().getTitle().equals(ChatColor.LIGHT_PURPLE + "Nuclear Atomizer")) return;

        event.setCancelled(true);

        ItemStack token = getToken();
        Player player = (Player) event.getWhoClicked();
        ReduxPlayer reduxPlayer = playerExists(player);
        boolean emptyInv = false;

        for(ItemStack is : player.getInventory().getContents())
            if (is == null) {
                emptyInv = true;
                break;
            }

        if(!emptyInv){
            player.sendMessage(ChatColor.RED + "Come back when you have a free inventory slot!");
            player.closeInventory();
            return;
        }


        if(event.getCurrentItem().equals(Atomizer.totallyLegitGem)){
            if (player.getInventory().containsAtLeast(token, 576) && reduxPlayer.getPlayerGold() >= 32000000){
                for (int i = 0; i < 576; i++) {
                    player.getInventory().removeItem(token);
                }

                reduxPlayer.setPlayerGold((int) (reduxPlayer.getPlayerGold() - 32000000));

                player.sendMessage(ChatColor.GREEN + "Purchased successfully!");
                Sounds.PRESTIGE.play(player);
                player.getInventory().addItem(enchants.gem);

            }else{
                player.sendMessage(ChatColor.RED + "You can't afford this!");
                player.closeInventory();
            }
        }else if(event.getCurrentItem().equals(Atomizer.heist)){
            player.openInventory(Atomizer.heistInventory(player));
            return;
        }else if(event.getCurrentItem().equals(Atomizer.beacon)){
            player.openInventory(Atomizer.boosterInventory(player));
            return;
        }

        reduxPlayer.refreshScoreBoard();
        event.setCancelled(true);
    }

    @EventHandler
    public void cactus(InventoryClickEvent event){
        if(event.getClickedInventory().getTitle() != null && event.getClickedInventory().getTitle().equals(ChatColor.GRAY + "Philosopher's Cactus")){
            event.setCancelled(true);
            Player player = (Player) event.getWhoClicked();
            Inventory inventory = player.getInventory();

            if(event.getCurrentItem().equals(enchants.fresh_reds)){
                player.closeInventory();
                event.getWhoClicked().getInventory().removeItem(enchants.cactus);
                player.getInventory().addItem(enchants.fresh_reds);
            }else if(event.getCurrentItem().equals(enchants.fresh_blues)){
                player.closeInventory();
                event.getWhoClicked().getInventory().removeItem(enchants.cactus);
                player.getInventory().addItem(enchants.fresh_blues);
            }else if(event.getCurrentItem().equals(enchants.fresh_greens)){
                player.closeInventory();
                event.getWhoClicked().getInventory().removeItem(enchants.cactus);
                player.getInventory().addItem(enchants.fresh_greens);
            }else if(event.getCurrentItem().equals(enchants.fresh_yellows)){
                player.closeInventory();
                player.getInventory().removeItem(enchants.cactus);
                player.getInventory().addItem(enchants.fresh_yellows);
            }else if(event.getCurrentItem().equals(enchants.fresh_oranges)){
                player.closeInventory();
                event.getWhoClicked().getInventory().removeItem(enchants.cactus);
                player.getInventory().addItem(enchants.fresh_oranges);
            }



        }
    }

}

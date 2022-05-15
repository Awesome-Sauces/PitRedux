package com.alpha.redux.events;

import com.alpha.redux.items.itemManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class ArmorJoin {

    public static void GiveChain(Player player){
        Inventory inv = player.getInventory();

        try{
            if(CheckChainBoots(player)){
                player.getInventory().setBoots(itemManager.DiamondBoots);
            }

            if(CheckChainChestplate(player)){
                player.getInventory().setChestplate(itemManager.DiamondChestplate);
            }


            if(CheckIronLeggings(player)){
                player.getInventory().setLeggings(itemManager.DiamondLeggings);
            }

            if (!player.getInventory().containsAtLeast(itemManager.DiamondSword, 1)) {
                player.getInventory().addItem(itemManager.DiamondSword);
            }
        }catch (Exception e){

        }

    }

    public static boolean CheckChainBoots(Player player){
        try{
            if(player.getInventory().getBoots() == null){
                return true;
            }else if (player.getInventory().containsAtLeast(itemManager.DiamondBoots, 1)) {
                return false;
            }

            return false;

        }catch (Exception e){
            return false;
        }
    }

    public static boolean CheckChainChestplate(Player player){
        try{
            if(player.getInventory().getChestplate() == null){
                return true;
            }else if (player.getInventory().containsAtLeast(itemManager.DiamondChestplate, 1)) {
                return false;
            }

            return false;

        }catch (Exception e){
            return false;
        }
    }

    public static boolean CheckIronLeggings(Player player){
        try{
            if(player.getInventory().getLeggings() == null){
                return true;
            }else if (player.getInventory().containsAtLeast(itemManager.DiamondLeggings, 1)) {
                return false;
            }

            return false;

        }catch (Exception e){
            return false;
        }
    }


}

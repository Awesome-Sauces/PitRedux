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
                player.getInventory().setBoots(itemManager.IronBoots);
            }

            if(CheckChainChestplate(player)){
                player.getInventory().setChestplate(itemManager.IronChestplate);
            }


            if(CheckIronLeggings(player)){
                player.getInventory().setLeggings(itemManager.IronLeggings);
            }

            if(CheckIronHelmet(player)){
                player.getInventory().setHelmet(itemManager.IronHelmet);
            }

            if (!player.getInventory().containsAtLeast(itemManager.Bow, 1)) {
                player.getInventory().addItem(itemManager.Bow);
            }

            if (!player.getInventory().containsAtLeast(itemManager.IronSword, 1)) {
                player.getInventory().addItem(itemManager.IronSword);
            }

            if (!player.getInventory().containsAtLeast(new ItemStack(Material.ARROW), 1)) {
                player.getInventory().addItem(new ItemStack(Material.ARROW));
            }
        }catch (Exception e){

        }

    }

    public static boolean CheckIronHelmet(Player player){
        try{
            if(player.getInventory().getHelmet() == null){
                return true;
            }else if (player.getInventory().containsAtLeast(itemManager.DiamondHelmet, 1)) {
                return false;
            }

            return false;

        }catch (Exception e){
            return false;
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

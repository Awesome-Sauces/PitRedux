package com.alpha.redux.well;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import java.util.List;

import static com.alpha.redux.well.bowRoll.rollBowEnchants;
import static com.alpha.redux.well.randomROLL.*;
import static com.alpha.redux.well.swordLore.*;

public class mysticWell {

    public static ItemStack createDrop(Player player){
        ItemStack item = new ItemStack(Material.LEATHER_LEGGINGS, 1);
        LeatherArmorMeta meta = (LeatherArmorMeta) item.getItemMeta();
        meta.setColor(Color.RED);
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&cTier I Red Pants"));

        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS);
        meta.spigot().setUnbreakable(true);
        meta.setLore(randomRoll(player));
        item.setItemMeta(meta);
        //Shaped Recipe

        return item;
    }

    public static ItemStack createDropI(ItemStack item, Player player) {
        LeatherArmorMeta meta = (LeatherArmorMeta) item.getItemMeta();
        meta.setColor(Color.RED);
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&',"&cTier II Red Pants"));

        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS);
        meta.spigot().setUnbreakable(true);
        meta.setLore(randomRollI(item.getItemMeta().getLore(), player));
        item.setItemMeta(meta);
        //Shaped Recipe

        return item;
    }

    public static ItemStack createDropII(ItemStack item, Player player) {
        LeatherArmorMeta meta = (LeatherArmorMeta) item.getItemMeta();
        meta.setColor(Color.RED);
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&cTier III Red Pants"));

        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS);
        meta.spigot().setUnbreakable(true);
        meta.setLore(randomRollII(item.getItemMeta().getLore(), player));
        item.setItemMeta(meta);
        //Shaped Recipe

        return item;
    }

    public static ItemStack createSword(Player player){
        ItemStack item = new ItemStack(Material.GOLD_SWORD, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&cTier I Sword"));
        item.addEnchantment(Enchantment.DAMAGE_ALL, 2);
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS);
        meta.spigot().setUnbreakable(true);
        meta.setLore(TierISword(player));
        item.setItemMeta(meta);
        //Shaped Recipe

        return item;
    }

    public static ItemStack createSwordI(ItemStack item, Player player) {
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&cTier II Sword"));

        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS);
        meta.spigot().setUnbreakable(true);
        meta.setLore(TierIISword(item.getItemMeta().getLore(), player));
        item.setItemMeta(meta);
        //Shaped Recipe

        return item;
    }

    public static ItemStack createSwordII(ItemStack item, Player player) {
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&cTier III Sword"));

        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS);
        meta.spigot().setUnbreakable(true);
        meta.setLore(TierIIISword(item.getItemMeta().getLore(), player));
        item.setItemMeta(meta);
        //Shaped Recipe

        return item;
    }


    public static ItemStack createBow(Player player){
        ItemStack item = new ItemStack(Material.BOW, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&cTier I Bow"));
        item.addEnchantment(Enchantment.ARROW_INFINITE, 1);
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS);
        meta.spigot().setUnbreakable(true);
        meta.setLore(rollBowEnchants(player, true, null));
        item.setItemMeta(meta);
        //Shaped Recipe

        return item;
    }

    public static ItemStack createBowI(ItemStack item, Player player) {
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&cTier II Bow"));
        item.addEnchantment(Enchantment.ARROW_INFINITE, 1);
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS);
        meta.spigot().setUnbreakable(true);
        meta.setLore(rollBowEnchants(player, false, item.getItemMeta().getLore()));
        item.setItemMeta(meta);
        //Shaped Recipe

        return item;
    }

    public static ItemStack createBowII(ItemStack item, Player player) {
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&cTier III Bow"));
        item.addEnchantment(Enchantment.ARROW_INFINITE, 1);
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS);
        meta.spigot().setUnbreakable(true);
        meta.setLore(rollBowEnchants(player, false, item.getItemMeta().getLore()));
        item.setItemMeta(meta);
        //Shaped Recipe

        return item;
    }
}

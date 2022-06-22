package com.alpha.redux.commands.crates;

import com.alpha.redux.items.enchants;
import com.alpha.redux.items.itemManager;
import com.alpha.redux.renownShop.RenownItems;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import java.util.ArrayList;
import java.util.List;

public class CrateItems {
    public ItemStack getJewelPant(){
        return enchants.jewl_pant;
    }

    public ItemStack getJewelSword(){
        return enchants.jewl_sword;
    }

    public ItemStack getPitBlob(){
        ItemStack item = new ItemStack(Material.LEATHER_LEGGINGS, 1);
        LeatherArmorMeta meta = (LeatherArmorMeta) item.getItemMeta();
        meta.setColor(Color.AQUA);
        meta.setDisplayName("§cTier I Aqua Pants");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.translateAlternateColorCodes('&', "§7Lives: §a5§7/5"));
        lore.add(" ");
        lore.add("§dRARE! §9Pit Blob III");
        lore.add("§7Kills respawn §aThe Blob§7. This");
        lore.add("§7slimy pet will follow you around");
        lore.add("§7and kill your enemies. §aThe Blob");
        lore.add("§7grows and gains health for every");
        lore.add("§7enemy you kill.");

        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS);
        meta.spigot().setUnbreakable(true);
        meta.setLore(lore);
        item.setItemMeta(meta);

        return item;
    }

    public ItemStack getFeather(){
        return itemManager.feather;
    }

    public ItemStack getVile(){
        return enchants.vile;
    }

    public ItemStack getUber(){
        return RenownItems.UberDrop();
    }

    public ItemStack getDiamondHelmet() {
        ItemStack item = new ItemStack(Material.DIAMOND_HELMET, 1);
        List<String> lore = new ArrayList<>();
        ItemMeta meta = item.getItemMeta();
        meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2, false);
        meta.spigot().setUnbreakable(true);
        item.setItemMeta(meta);
        return item;
    }
    public ItemStack getDiamondChestplate() {
        ItemStack item = new ItemStack(Material.DIAMOND_CHESTPLATE, 1);
        ItemMeta meta = item.getItemMeta();
        meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2, false);
        meta.spigot().setUnbreakable(true);
        item.setItemMeta(meta);
        return item;
    }
    public ItemStack getDiamondLeggings() {
        ItemStack item = new ItemStack(Material.DIAMOND_LEGGINGS, 1);
        ItemMeta meta = item.getItemMeta();
        meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2, false);
        meta.spigot().setUnbreakable(true);
        item.setItemMeta(meta);
        return item;
    }
    public ItemStack getDiamondBoots() {
        ItemStack item = new ItemStack(Material.DIAMOND_BOOTS, 1);
        ItemMeta meta = item.getItemMeta();
        meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2, false);
        meta.spigot().setUnbreakable(true);
        item.setItemMeta(meta);
        return item;
    }
    
}

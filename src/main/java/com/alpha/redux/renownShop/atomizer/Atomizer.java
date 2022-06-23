package com.alpha.redux.renownShop.atomizer;

import com.alpha.redux.apis.advancedInventory;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import static com.alpha.redux.well.EnchantingMechanics.getEnchants;

public class Atomizer {

    public static ItemStack totallyLegitGem = advancedInventory.ItemMaker(Material.EMERALD, ChatColor.RED + "Totally Legit Gem", ChatColor.translateAlternateColorCodes('&',
                    "&7Adds &d1 tier&7 to a\n &c&lrandom &7mystic enchant.\n\n&7Renown Cost: &e576 Renown Tokens\n&7Gold Cost: &632,000,000g"),
            1, true);

    public static ItemStack heist = advancedInventory.ItemMaker(Material.GOLD_BOOTS, ChatColor.RED + "Heist Master", ChatColor.translateAlternateColorCodes('&',
                    "&7Harnesses the power of the stars\n&7to give you huge stat\n&7boosters and buffs\n\n&7Click to view the shop!"),
            1, true);

    public static ItemStack heistOne = advancedInventory.ItemMaker(Material.GOLD_BOOTS, ChatColor.RED + "Heist Master", ChatColor.translateAlternateColorCodes('&',
                    "&e25% &7chance to double &c&lSOME &7rewards!\n\n&7Renown Cost: &e640 Renown Tokens\n&7Gold Cost: &648,000,000g"),
            1, true);

    public static ItemStack heistTwo = advancedInventory.ItemMaker(Material.GOLD_BOOTS, ChatColor.RED + "Heist Master II", ChatColor.translateAlternateColorCodes('&',
                    "&e50% &7chance to double &c&lSOME &7rewards!\n\n&7Renown Cost: &e1280 Renown Tokens\n&7Gold Cost: &696,000,000g"),
            1, true);

    public static ItemStack heistThree = advancedInventory.ItemMaker(Material.GOLD_BOOTS, ChatColor.RED + "Heist Master III", ChatColor.translateAlternateColorCodes('&',
                    "&e75% &7chance to double &c&lSOME &7rewards!\n\n&7Renown Cost: &e1920 Renown Tokens\n&7Gold Cost: &6144,000,000g"),
            1, true);

    public static ItemStack beacon = advancedInventory.ItemMaker(Material.BEACON, ChatColor.RED + "Booster Atoms", ChatColor.translateAlternateColorCodes('&',
                    "&7Stat multipliers and boosters. Decay\n&7over a span of 10 minutes.\n\n&7Click to view the shop!"),
            1, true);

    public static ItemStack xpBoost = advancedInventory.DyeMaker((short) 6, ChatColor.RED + "Experience Atom", ChatColor.translateAlternateColorCodes('&',
            "&7Doubles all experience income. Decays\n&7over a span of 10 minutes.\n\n&7Renown Cost: &e192 Renown Tokens"));

    public static ItemStack goldBoost = advancedInventory.DyeMaker((short) 14, ChatColor.RED + "Gold Atom", ChatColor.translateAlternateColorCodes('&',
            "&7Doubles all gold income. Decays\n&7over a span of 10 minutes.\n\n&7Renown Cost: &e128 Renown Tokens"));

    public static ItemStack mysticBoost = advancedInventory.ItemMaker(Material.ENCHANTMENT_TABLE, ChatColor.RED + "Mystic Atom", ChatColor.translateAlternateColorCodes('&',
                    "&7Doubles your mystic drop chance. Decays\n&7over a span of 10 minutes.\n\n&7Renown Cost: &e256 Renown Tokens"),
            1, true);

    public static Inventory inventoryConstructor(Player player){

        Inventory gui = advancedInventory.inv(player, 27, ChatColor.LIGHT_PURPLE + "Nuclear Atomizer");
        ItemStack base_glass = advancedInventory.cGlass();

        gui.setItem(10, totallyLegitGem);

        gui.setItem(13, heist);

        gui.setItem(16, beacon);

        for (int i = 0; i < gui.getSize(); i++) if (gui.getItem(i) == null) gui.setItem(i, base_glass);

        return gui;
    }

    public static Inventory heistInventory(Player player){

        Inventory gui = advancedInventory.inv(player, 27, ChatColor.LIGHT_PURPLE + "Heist Master");
        ItemStack base_glass = advancedInventory.cGlass();

        gui.setItem(10, heistOne);

        gui.setItem(13, heistTwo);

        gui.setItem(16, heistThree);

        for (int i = 0; i < gui.getSize(); i++) if (gui.getItem(i) == null) gui.setItem(i, base_glass);

        return gui;
    }

    public static Inventory boosterInventory(Player player){

        Inventory gui = advancedInventory.inv(player, 27, ChatColor.LIGHT_PURPLE + "Booster Atoms");
        ItemStack base_glass = advancedInventory.cGlass();

        gui.setItem(10, xpBoost);

        gui.setItem(13, mysticBoost);

        gui.setItem(16, goldBoost);

        for (int i = 0; i < gui.getSize(); i++) if (gui.getItem(i) == null) gui.setItem(i, base_glass);

        return gui;
    }

}

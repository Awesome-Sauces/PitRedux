package com.alpha.redux.MenuClicks.Cactus;

import com.alpha.redux.apis.advancedInventory;
import com.alpha.redux.items.enchants;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class CactusRunTime {

    public static Inventory inventoryConstructor(Player player){

        Inventory gui = advancedInventory.inv(player, 27, ChatColor.GRAY + "Philosopher's Cactus");
        ItemStack base_glass = advancedInventory.cGlass();

        gui.setItem(11, enchants.fresh_greens);
        gui.setItem(12, enchants.fresh_blues);
        gui.setItem(13, enchants.fresh_reds);
        gui.setItem(14, enchants.fresh_oranges);
        gui.setItem(15, enchants.fresh_yellows);

        for (int i = 0; i < gui.getSize(); i++) if (gui.getItem(i) == null) gui.setItem(i, base_glass);

        return gui;
    }
}

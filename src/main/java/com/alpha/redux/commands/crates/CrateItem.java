package com.alpha.redux.commands.crates;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public enum CrateItem {

    JEWEL_SWORD(null, 1),
    JEWEL_PANT(null, 1);

    public ItemStack item;
    public int amount;

    CrateItem(ItemStack item, int amount){

    }
}

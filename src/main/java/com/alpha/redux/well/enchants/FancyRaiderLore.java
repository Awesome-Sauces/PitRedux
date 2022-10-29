package com.alpha.redux.well.enchants;

import com.alpha.redux.eventManagers.ReduxDamageEvent;
import org.bukkit.Material;

import static com.alpha.redux.events.boards.integerToRoman;

public class FancyRaiderLore extends PitEnchant{

    @Override
    public void run(ReduxDamageEvent event, int level) {
        double multiplier = 0;

        if (level > 2) {
            multiplier += (level * 5);
        }else {multiplier += level*5;}


        if(event.getDefenders().getHelmet() != null && event.getDefenders().getHelmet().getType().equals(Material.LEATHER_HELMET)
                || event.getDefenders().getChestplate() != null &&  event.getDefenders().getChestplate().getType().equals(Material.LEATHER_CHESTPLATE)
                || event.getDefenders().getLeggings() != null && event.getDefenders().getLeggings().getType().equals(Material.LEATHER_LEGGINGS)
                || event.getDefenders().getBoots() != null && event.getDefenders().getBoots().getType().equals(Material.LEATHER_BOOTS))
            event.addReduxDamage(event.getReduxDamage() * (multiplier/100));
    }

    @Override
    public void init() {
        rarity = EnchantRarity.NORMAL;
    }

    @Override
    public String title(int level) {
        String tier = "";
        if (level > 1){tier += " " + integerToRoman(level);}

        return "&9Fancy Raider" + tier;
    }

    @Override
    public String lore(int level) {
        String tier = "";
        if (level > 1){tier += " " + integerToRoman(level);}

        String multiplier = "";

        if (level > 2) {
            multiplier += String.valueOf(level * 5);
        }else {multiplier += String.valueOf(level*5);}

        String lore = "&9Fancy Raider" + tier + "\n" +
                "&7Deal &c" + multiplier + "%&7 damage vs. players" +
                "\n&7wearing leather armor" + "\n&7";

        return colorCode(lore);
    }
}

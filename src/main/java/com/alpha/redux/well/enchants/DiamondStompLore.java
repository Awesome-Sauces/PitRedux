package com.alpha.redux.well.enchants;

import com.alpha.redux.eventManagers.ReduxDamageEvent;
import org.bukkit.Material;

import static com.alpha.redux.events.boards.integerToRoman;

public class DiamondStompLore extends PitEnchant{

    @Override
    public void run(ReduxDamageEvent event, int level) {
        int multiplier = 0;

        if (level > 2) {
            multiplier += (level * 8) + 1;
        }else {multiplier += level*6;}

        multiplier = multiplier / 100;

        if(event.getDefenders().getHelmet() != null && event.getDefenders().getHelmet().getType().equals(Material.DIAMOND_HELMET)
                || event.getDefenders().getChestplate() != null &&  event.getDefenders().getChestplate().getType().equals(Material.DIAMOND_CHESTPLATE)
                || event.getDefenders().getLeggings() != null && event.getDefenders().getLeggings().getType().equals(Material.DIAMOND_LEGGINGS)
                || event.getDefenders().getBoots() != null && event.getDefenders().getBoots().getType().equals(Material.DIAMOND_BOOTS))
            event.addReduxDamage(event.getReduxDamage() * multiplier);
    }

    @Override
    public String lore(int level) {
        String tier = "";
        if (level > 1){tier += " " + integerToRoman(level);}

        String multiplier = "";

        if (level > 2) {
            multiplier += String.valueOf((level * 8) + 1);
        }else {multiplier += String.valueOf(level*6);}

        String lore = "&9Diamond Stomp" + tier + "\n" +
                "&7Deal &c" + multiplier + "%&7 damage vs. players" +
                "\n&7wearing diamond armor";

        return colorCode(lore);
    }
}

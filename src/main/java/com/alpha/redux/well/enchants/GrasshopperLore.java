package com.alpha.redux.well.enchants;

import com.alpha.redux.eventManagers.ReduxDamageEvent;
import org.bukkit.Material;

import static com.alpha.redux.events.boards.integerToRoman;

public class GrasshopperLore extends PitEnchant{

    @Override
    public void run(ReduxDamageEvent event, int level) {
        if(event.getAttacker().getPlayerObject().getWorld().getBlockAt(event.getAttacker().getPlayerObject().getLocation().add(0,-1,0)).getType().equals(Material.GRASS) ||
                event.getDefenders().getPlayerObject().getWorld().getBlockAt(event.getDefenders().getPlayerObject().getLocation().add(0,-1,0)).getType().equals(Material.GRASS)){
            double damage = (level*5);
            event.addReduxDamage(event.getReduxDamage()*(damage/100));
        }
    }

    @Override
    public void init() {
        rarity = EnchantRarity.NORMAL;
    }

    @Override
    public String title(int level) {
        String tier = "";
        if (level > 1){tier += " " + integerToRoman(level);}

        return "&9Grasshopper" + tier;
    }

    @Override
    public String lore(int level) {
        String tier = "";
        if (level > 1){tier += " " + integerToRoman(level);}

        String multiplier = String.valueOf(level*5);

        String lore = "&9Grasshopper" + tier + "\n" +
                "&7Deal &c+" + multiplier + "% &7damage when you or\n" +
                "&7your victim are standing on grass" + "\n&7";

        return colorCode(lore);
    }
}


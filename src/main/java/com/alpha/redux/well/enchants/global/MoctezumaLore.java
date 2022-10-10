package com.alpha.redux.well.enchants.global;

import com.alpha.redux.eventManagers.ReduxDamageEvent;
import com.alpha.redux.well.enchants.EnchantRarity;
import com.alpha.redux.well.enchants.PitEnchant;

import static com.alpha.redux.events.boards.integerToRoman;

public class MoctezumaLore extends PitEnchant {
    @Override
    public void run(ReduxDamageEvent event, int level) {

    }

    @Override
    public void init() {
        EnchantRarity rarity = EnchantRarity.NORMAL;
    }

    @Override
    public String title(int level) {
        String tier = "";
        if (level > 1){tier += " " + integerToRoman(level);}

        return "&9Moctezuma" + tier;
    }

    @Override
    public String lore(int level) {
        String tier = "";
        if (level > 1){tier += " " + integerToRoman(level);}

        String multiplier = String.valueOf(6*level);

        String lore = "&9Moctezuma" + tier + "\n" +
                "&7Earn &6+" + multiplier + "g&7 on kill (assists\n" +
                "&7excluded)";

        return colorCode(lore);
    }
}

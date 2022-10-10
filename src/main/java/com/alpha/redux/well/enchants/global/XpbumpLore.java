package com.alpha.redux.well.enchants.global;

import com.alpha.redux.eventManagers.ReduxDamageEvent;
import com.alpha.redux.well.enchants.EnchantRarity;
import com.alpha.redux.well.enchants.PitEnchant;

import static com.alpha.redux.events.boards.integerToRoman;

public class XpbumpLore extends PitEnchant {
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

        return "&9XP Bump" + tier;
    }

    @Override
    public String lore(int level) {
        String tier = "";
        if (level > 1){tier += " " + integerToRoman(level);}

        String multiplier = String.valueOf(2*level);

        String lore = "&9XP Bump" + tier + "\n" +
                "&7Earn &b+" + multiplier + " XP &7per\n" +
                "&7kill";

        return colorCode(lore);
    }
}

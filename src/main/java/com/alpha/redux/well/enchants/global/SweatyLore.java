package com.alpha.redux.well.enchants.global;

import com.alpha.redux.eventManagers.ReduxDamageEvent;
import com.alpha.redux.well.enchants.EnchantRarity;
import com.alpha.redux.well.enchants.PitEnchant;

import static com.alpha.redux.events.boards.integerToRoman;

public class SweatyLore extends PitEnchant {
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

        return "&9Sweaty" + tier;
    }

    @Override
    public String lore(int level) {
        String tier = "";
        if (level > 1){tier += " " + integerToRoman(level);}

        String multiplier = String.valueOf(15*level);

        String max = String.valueOf(25*(level+1));

        String lore = "&9Sweaty" + tier + "\n" +
                "&7Earn &b+" + multiplier + "% XP&7 from streak XP\n" +
                "&bbonus and &b+" + max + " max XP&7 per kill";

        return colorCode(lore);
    }
}

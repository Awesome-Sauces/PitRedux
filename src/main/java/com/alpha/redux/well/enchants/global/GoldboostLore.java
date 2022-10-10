package com.alpha.redux.well.enchants.global;

import com.alpha.redux.eventManagers.ReduxDamageEvent;
import com.alpha.redux.well.enchants.EnchantRarity;
import com.alpha.redux.well.enchants.PitEnchant;

import static com.alpha.redux.events.boards.integerToRoman;

public class GoldboostLore extends PitEnchant {
    @Override
    public void run(ReduxDamageEvent event, int level) {

    }

    @Override
    public String title(int level) {
        String tier = "";
        if (level > 1){tier += " " + integerToRoman(level);}

        return "&9Gold Boost" + tier;
    }

    @Override
    public void init() {
        EnchantRarity rarity = EnchantRarity.NORMAL;
    }

    @Override
    public String lore(int level) {
        String tier = "";
        if (level > 1){tier += " " + integerToRoman(level);}

        String multiplier = String.valueOf(15*level);

        String lore = "&9Gold Boost" + tier + "\n" +
                "&7Earn &6+" + multiplier + "% gold (g)&7 from kill";

        return colorCode(lore);
    }
}

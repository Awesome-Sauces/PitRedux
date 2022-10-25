package com.alpha.redux.well.enchants.global;

import com.alpha.redux.eventManagers.ReduxDamageEvent;
import com.alpha.redux.well.enchants.EnchantRarity;
import com.alpha.redux.well.enchants.PitEnchant;

import static com.alpha.redux.events.boards.integerToRoman;

public class PantsRadarLore extends PitEnchant {
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

        return "&9Pants Radar" + tier;
    }

    @Override
    public String lore(int level) {
        String tier = "";
        if (level > 1){tier += " " + integerToRoman(level);}

        String multiplier = String.valueOf(30*level);

        String lore = "&9Pants Radar" + tier + "\n" +
                "&7Pants, golden swords and enchanted\n" +
                "&7bows drop &d+"+multiplier+"% &7more frequently"+ "\n&7";

        return colorCode(lore);
    }
}


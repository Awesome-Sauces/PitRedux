package com.alpha.redux.well.enchants;

import com.alpha.redux.eventManagers.ReduxDamageEvent;

import static com.alpha.redux.events.boards.integerToRoman;

public class MirrorLore extends PitEnchant{

    @Override
    public void run(ReduxDamageEvent event, int level) {

        double damage = level*.30;

        event.setReduxTrueDamage(event.getReduxTrueDamage()*damage);
    }

    @Override
    public void init() {
        rarity = EnchantRarity.NORMAL;
    }

    @Override
    public String title(int level) {
        String tier = "";
        if (level > 1){tier += " " + integerToRoman(level);}

        return "&9Mirror" + tier;
    }

    @Override
    public String lore(int level) {
        String tier = "";
        if (level > 1){tier += " " + integerToRoman(level);}

        String lore = "&9Mirror" + tier + "\n" +
                "&7You are immune to true damage" + "\n&7";

        return colorCode(lore);
    }
}
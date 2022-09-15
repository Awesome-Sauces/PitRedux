package com.alpha.redux.well.enchants;

import com.alpha.redux.eventManagers.ReduxDamageEvent;

import static com.alpha.redux.events.boards.integerToRoman;

public class SharpLore extends PitEnchant{

    @Override
    public void run(ReduxDamageEvent event, int level) {
        event.addReduxDamage(event.getReduxDamage() * ((float) (level * 4) / 100));
    }

    @Override
    public void init() {
        rarity = EnchantRarity.NORMAL;
    }

    @Override
    public String lore(int level) {
        String tier = "";
        if (level > 1){tier += " " + integerToRoman(level);}

        String multiplier = String.valueOf(level*4);

        String lore = "&9Sharp" + tier + "\n" +
                "&7Deal &c" + multiplier + "%&7 melee damage";

        return colorCode(lore);
    }
}

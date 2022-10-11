package com.alpha.redux.well.enchants;

import com.alpha.redux.eventManagers.ReduxDamageEvent;

import static com.alpha.redux.events.boards.integerToRoman;

public class SolitudeLore extends PitEnchant{

    @Override
    public void run(ReduxDamageEvent event, int level) {
        event.subtractReduxDamage(event.getReduxDamage()*((float)(40+((level-1)*10))/100));
    }

    @Override
    public void init() {
        rarity = EnchantRarity.RARE;
    }

    @Override
    public String title(int level) {
        String tier = "";
        if (level > 1){tier += " " + integerToRoman(level);}

        return "&dRARE! &9Solitude" + tier;
    }

    @Override
    public String lore(int level) {
        String tier = "";
        if (level > 1){tier += " " + integerToRoman(level);}

        String multiplier = String.valueOf(40+((level-1)*10));

        String lore = "&dRARE! &9Solitude" + tier + "\n" +
                "&7Receive &9-"+multiplier+"% &7damage when two\n" +
                "&7or less players are within 7\n" +
                "&7blocks." + "\n&7";

        return colorCode(lore);
    }
}
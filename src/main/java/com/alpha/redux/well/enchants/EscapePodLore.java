package com.alpha.redux.well.enchants;

import com.alpha.redux.eventManagers.ReduxDamageEvent;

import static com.alpha.redux.events.boards.integerToRoman;

public class EscapePodLore extends PitEnchant{

    @Override
    public void run(ReduxDamageEvent event, int level) {
        event.subtractReduxDamage(event.getReduxDamage()*((float)(25+((level-1)*25))/100));
    }

    @Override
    public void init() {
        rarity = EnchantRarity.RARE;
    }

    @Override
    public String title(int level) {
        String tier = "";
        if (level > 1){tier += " " + integerToRoman(level);}

        return "&dRARE! &9Escape Pod" + tier;
    }

    @Override
    public String lore(int level) {
        String tier = "";
        if (level > 1){tier += " " + integerToRoman(level);}

        String multiplier = String.valueOf(15+((level-1)*15));

        String lore = "&dRARE! &9Escape Pod" + tier + "\n" +
                "&7When hit below &c2 &7, launch\n" +
                "&7into the air dealing &c3 &7damage\n" +
                "&7to nearby enemies and gaining\n" +
                "&aRegen IV&7 ("+multiplier+"s), Can launch\n" +
                "&7once per life." + "\n&7";

        return colorCode(lore);
    }
}
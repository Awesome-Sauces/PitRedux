package com.alpha.redux.well.enchants;

import com.alpha.redux.eventManagers.ReduxDamageEvent;

import static com.alpha.redux.events.boards.integerToRoman;

public class LifestealLore extends PitEnchant{

    @Override
    public void run(ReduxDamageEvent event, int level) {

        event.getAttacker().getPlayerObject().setHealth(Math.min(event.getAttacker().getPlayerObject().getHealth()
                        + (event.getReduxDamage() * (getHealing(level) / 100D)),
                event.getAttacker().getPlayerObject().getMaxHealth()));
    }

    public double getHealing(int enchantLvl) {

//		return (int) (Math.pow(enchantLvl, 1.1) * 4);
        return enchantLvl * 3 + 1;
    }

    @Override
    public void init() {
        rarity = EnchantRarity.NORMAL;
    }

    @Override
    public String title(int level) {
        String tier = "";
        if (level > 1){tier += " " + integerToRoman(level);}

        return "&9Lifesteal" + tier;
    }

    @Override
    public String lore(int level) {
        String tier = "";
        if (level > 1){tier += " " + integerToRoman(level);}

        String multiplier = "";

        if (level > 2) {
            multiplier += String.valueOf((level*4) + 1);
        }else {multiplier += String.valueOf(level*4);}

        String lore = "&9Lifesteal" + tier + "\n" +
                "&7Heal for &c" + multiplier + "%&7 of damage dealt up" +
                "\n&7to &c1.5❤" + "\n&7";

        return colorCode(lore);
    }
}

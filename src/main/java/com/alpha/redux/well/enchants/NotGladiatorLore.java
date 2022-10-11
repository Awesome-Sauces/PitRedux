package com.alpha.redux.well.enchants;

import com.alpha.redux.eventManagers.ReduxDamageEvent;

import static com.alpha.redux.events.boards.integerToRoman;

public class NotGladiatorLore extends PitEnchant{

    @Override
    public void run(ReduxDamageEvent event, int level) {

        event.subtractReduxDamage(event.getReduxDamage() * (getMultiplier(event.getDefenders().getPlayerObject(), ((float)(1+((level-1)*.5))/100))/10));

    }

    private double getMultiplier(Player player, double multiplier){

        double power = 0;

        for(Entity entity : player.getNearbyEntities(15, 15, 15))
            if(entity instanceof Player) power += multiplier;

        return Math.min(power, multiplier * 10);
    }

    @Override
    public void init() {
        rarity = EnchantRarity.NORMAL;
    }

    @Override
    public String title(int level) {
        String tier = "";
        if (level > 1){tier += " " + integerToRoman(level);}

        return "&9\"Not\" Gladiator" + tier;
    }

    @Override
    public String lore(int level) {
        String tier = "";
        if (level > 1){tier += " " + integerToRoman(level);}

        String multiplier = String.valueOf(1+((level-1)*.5));

        String lore = "&9\"Not\" Gladiator" + tier + "\n" +
                "&7Receive &9-"+multiplier+"% damage per nearby\n" +
                "&7player (max 10 players)" + "\n&7";

        return colorCode(lore);
    }
}
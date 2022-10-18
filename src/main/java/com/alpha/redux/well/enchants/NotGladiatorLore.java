package com.alpha.redux.well.enchants;

import com.alpha.redux.eventManagers.ReduxDamageEvent;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import static com.alpha.redux.events.boards.integerToRoman;

public class NotGladiatorLore extends PitEnchant{

    @Override
    public void run(ReduxDamageEvent event, int level) {

        double damage = 1+((level-1)*.5);

        event.subtractReduxDamage(event.getReduxDamage() * (getMultiplier(event.getDefenders().getPlayerObject(), (damage/100))));

    }

    private double getMultiplier(Player player, double multiplier){

        double power = 0;

        for(Entity entity : player.getNearbyEntities(7, 7, 7))
            if(entity instanceof Player) power += multiplier;

        return Math.min(power, multiplier * 5);
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
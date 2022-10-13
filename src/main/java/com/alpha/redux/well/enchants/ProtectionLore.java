package com.alpha.redux.well.enchants;

import com.alpha.redux.eventManagers.ReduxDamageEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import static com.alpha.redux.events.boards.integerToRoman;

public class ProtectionLore extends PitEnchant{

    @Override
    public void run(ReduxDamageEvent event, int level) {

        double damage = 4+((level-1)*3);

        event.subtractReduxDamage(event.getReduxDamage()*(damage/100));
    }

    @Override
    public void init() {
        rarity = EnchantRarity.NORMAL;
    }

    @Override
    public String title(int level) {
        String tier = "";
        if (level > 1){tier += " " + integerToRoman(level);}

        return "&9Protection" + tier;
    }

    @Override
    public String lore(int level) {
        String tier = "";
        if (level > 1){tier += " " + integerToRoman(level);}

        String multiplier = String.valueOf(4+((level-1)*3));

        String lore = "&9Protection" + tier + "\n" +
                "&7Receive &9-"+multiplier+"%&7 damage" + "\n&7";

        return colorCode(lore);
    }
}

package com.alpha.redux.well.enchants;

import com.alpha.redux.eventManagers.ReduxDamageEvent;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public abstract class PitEnchant {

    public String colorCode(String text){
        return ChatColor.translateAlternateColorCodes('&', text);
    }

    public abstract void run(ReduxDamageEvent event, int level);

    public abstract String lore(int level);
}

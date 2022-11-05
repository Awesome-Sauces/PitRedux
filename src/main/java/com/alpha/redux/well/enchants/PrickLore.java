package com.alpha.redux.well.enchants;

import com.alpha.redux.apis.Sounds;
import com.alpha.redux.entityHandlers.TrueDamage.TrueDamageHandler;
import com.alpha.redux.eventManagers.ReduxDamageEvent;
import com.alpha.redux.playerdata.economy;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

import static com.alpha.redux.entityHandlers.ReduxPlayerHandler.playerExists;
import static com.alpha.redux.events.boards.integerToRoman;

public class PrickLore extends PitEnchant{

    @Override
    public void run(ReduxDamageEvent event, int level) {

        double damage = (double) ((5+(level*15)))*2;

        if (event.getDefenders().getPrickCD()){
            event.getDefenders().setPrickCD();
            new TrueDamageHandler(event.getDefenders(), event.getAttacker(), (damage/100), 0).run();
            new BukkitRunnable() {
                @Override
                public void run() {
                    event.getDefenders().setPrickCD();
                }
            }.runTaskLater(economy.getPlugin(), 11L);
        }

    }

    @Override
    public void init() {
        rarity = EnchantRarity.NORMAL;
    }

    @Override
    public String title(int level) {
        String tier = "";
        if (level > 1){tier += " " + integerToRoman(level);}

        return "&9Prick" + tier;
    }

    @Override
    public String lore(int level) {
        String tier = "";
        if (level > 1){tier += " " + integerToRoman(level);}

        double multiplier = (double) ((5+(level*15)));

        Bukkit.broadcastMessage(String.valueOf(multiplier/100));

        String lore = "&9Prick" + tier + "\n" +
                "&7Enemies hitting you receive\n&c"+(multiplier/100)+"❤ &7true damage" + "\n&7";

        return colorCode(lore);
    }
}


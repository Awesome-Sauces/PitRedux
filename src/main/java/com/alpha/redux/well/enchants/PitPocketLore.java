package com.alpha.redux.well.enchants;

import com.alpha.redux.entityHandlers.TrueDamage.TrueDamageHandler;
import com.alpha.redux.eventManagers.ReduxDamageEvent;
import com.alpha.redux.playerdata.economy;
import org.bukkit.scheduler.BukkitRunnable;

import static com.alpha.redux.events.boards.integerToRoman;

public class PitPocketLore extends PitEnchant{

    @Override
    public void run(ReduxDamageEvent event, int level) {

        if (event.getAttacker().getPitPocketCD()){
            event.getAttacker().setPitPocketCD();

            int gold = 10+(level*5);

            event.getAttacker().getPlayerObject().sendMessage(colorCode("&6&lPITPOCKET! &7you stole &6" + gold + "g &7from &6" + event.getDefenders().getPlayerObject().getDisplayName()));

            event.getAttacker().addPlayerGold(gold);

            new BukkitRunnable() {
                @Override
                public void run() {
                    event.getAttacker().setPitPocketCD();
                }
            }.runTaskLater(economy.getPlugin(), 30000L);
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

        return "&9Pitpocket" + tier;
    }

    @Override
    public String lore(int level) {
        String tier = "";
        if (level > 1){tier += " " + integerToRoman(level);}

        String multiplier = String.valueOf(10+(level*5));

        String lore = "&9Pitpocket" + tier + "\n" +
                "&7Steal &6" + multiplier + "g &7on melee hit (25s\n" +
                "&7cooldown)" + "\n&7";

        return colorCode(lore);
    }
}


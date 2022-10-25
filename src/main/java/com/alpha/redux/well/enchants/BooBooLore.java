package com.alpha.redux.well.enchants;

import com.alpha.redux.entityHandlers.ReduxPlayer;
import com.alpha.redux.eventManagers.ReduxDamageEvent;
import com.alpha.redux.playerdata.economy;
import com.alpha.redux.redux;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import static com.alpha.redux.events.boards.integerToRoman;

public class BooBooLore extends PitEnchant{

    @Override
    public void run(ReduxDamageEvent event, int level) {
        if (event.getDefenders().getBooCD()){
            event.getDefenders().setBooCD();

            event.getDefenders().getPlayerObject().setHealth(Math.min(20,
                    event.getDefenders().getPlayerObject().getHealth()+(.25*level)));

            new BukkitRunnable() {
                @Override
                public void run() {
                    event.getDefenders().setBooCD();
                }
            }.runTaskLater(redux.INSTANCE, 11L);
        }

        //if (level==1) event.getDefenders().getPlayerObject().setHealth(Math.min(event.getDefenders().getPlayerObject().getHealth()+.5, event.getDefenders().getPlayerObject().getMaxHealth()));
    }

    @Override
    public void init() {
        rarity = EnchantRarity.NORMAL;
    }

    @Override
    public String title(int level) {
        String tier = "";
        if (level > 1){tier += " " + integerToRoman(level);}

        return "&9Boo-boo" + tier;
    }

    @Override
    public String lore(int level) {
        String tier = "";
        if (level > 1){tier += " " + integerToRoman(level);}

        String multiplier = String.valueOf(.25*level);

        String lore = "&9Boo-boo" + tier + "\n" +
                "&7Heal &c" + multiplier + "❤ when hit."+ "\n&7";

        return colorCode(lore);
    }
}


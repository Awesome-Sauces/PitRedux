package com.alpha.redux.well.enchants;

import com.alpha.redux.eventManagers.ReduxDamageEvent;
import com.alpha.redux.playerdata.economy;
import org.bukkit.scheduler.BukkitRunnable;

import static com.alpha.redux.events.boards.integerToRoman;

public class RegularityLore extends PitEnchant{

    @Override
    public void run(ReduxDamageEvent event, int level) {

        triggerAttack(event, ((double) (25+((level-1)*25))/100));

    }

    public void triggerAttack(ReduxDamageEvent event, double multiplier){
        if (event.getAttacker().getRegCD()){
            event.getDefenders().getPlayerObject().damage(0);
            event.getAttacker().setRegCD();
            event.getDefenders().getPlayerObject().setNoDamageTicks(0);
            event.getDefenders().getPlayerObject().damage((event.getReduxDamage()) * multiplier, event.getAttacker().getPlayerObject());
            new BukkitRunnable() {
                @Override
                public void run() {
                    event.getAttacker().setRegCD();
                }
            }.runTaskLater(economy.getPlugin(), 11L);
        }
    }

    @Override
    public void init() {
        rarity = EnchantRarity.RARE;
    }

    @Override
    public String title(int level) {
        String tier = "";
        if (level > 1){tier += " " + integerToRoman(level);}

        return "&dRARE! &9Regularity" + tier;
    }

    @Override
    public String lore(int level) {
        String tier = "";
        if (level > 1){tier += " " + integerToRoman(level);}

        String multiplier = String.valueOf(25+((level-1)*25));

        String lore = "&dRARE! &9Regularity" + tier + "\n" +
                "&7If the final damage of your strike\n" +
                "&7deals less than &c1.5(HEART_EMOJI)&7 &7damage,\n" +
                "&7strike again in &a0.1s &7for &c"+multiplier+"%\n" +
                "&7damage" + "\n&7";

        return colorCode(lore);
    }
}
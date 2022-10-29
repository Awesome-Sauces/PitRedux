package com.alpha.redux.well.enchants;

import com.alpha.redux.entityHandlers.MysticHandler.Swords.Perun;
import com.alpha.redux.entityHandlers.ReduxPlayer;
import com.alpha.redux.eventManagers.ReduxDamageEvent;
import com.alpha.redux.playerdata.economy;
import org.bukkit.scheduler.BukkitRunnable;

import static com.alpha.redux.apis.strikeLight.strikeLightningForPlayers;
import static com.alpha.redux.entityHandlers.MysticHandler.Swords.Perun.hitCounter;
import static com.alpha.redux.events.boards.integerToRoman;

public class PerunLore extends PitEnchant{

    @Override
    public void run(ReduxDamageEvent event, int level) {

        int count = 5;

        if (perunCooldown(event.getAttacker()) && level > 1){count = 4;}

        addCounter(event);

        if(trigger(event, count)){
            event.addReduxTrueDamage(level*2);
        }
    }

    private boolean perunCooldown(ReduxPlayer owner){
        if (owner.getPerunCD()){
            owner.setPerunCD();
            new BukkitRunnable() {
                @Override
                public void run() {
                    owner.setPerunCD();
                }
            }.runTaskLater(economy.getPlugin(), 5L);
            return true;
        }

        return false;
    }

    private void addCounter(ReduxDamageEvent event){
        if(hitCounter.containsKey(event.getAttacker().getPlayerUUID())){
            hitCounter.put(event.getAttacker().getPlayerUUID(), hitCounter.get(event.getAttacker().getPlayerUUID()) + 1);
        }else{
            hitCounter.put(event.getAttacker().getPlayerUUID(), 1);
        }
    }

    private boolean trigger(ReduxDamageEvent event, int count){
        if(hitCounter.get(event.getAttacker().getPlayerUUID()) >= count){
            hitCounter.put(event.getAttacker().getPlayerUUID(), 0);
            strikeLightningForPlayers(event.getDefenders().getPlayerObject().getLocation(), event.getAttacker().getPlayerObject(), event.getDefenders().getPlayerObject());
            return true;
        }else return false;
    }

    @Override
    public void init() {
        rarity = EnchantRarity.RARE;
    }

    @Override
    public String title(int level) {
        String tier = "";
        if (level > 1){tier += " " + integerToRoman(level);}

        return "&dRARE! &9Combo: Perun's Wrath" + tier;
    }

    @Override
    public String lore(int level) {

        String tier = "";
        if (level > 1){tier += " " + integerToRoman(level);}

        String multiplier = String.valueOf(level);

        String hit = "";

        if (level > 1){
            hit = "fourth";
        }else {hit = "fifth";}

        String lore = "&dRARE! &9Combo: Perun's Wrath" + tier + "\n" +
                "&7Each &e" + hit + "&7 hit strikes" +
                "\n&elightning&7 for &c" + multiplier + "(HEART_EMOJI)&7." +
                "\n&7&oLightning deals true damage" + "\n&7";

        return colorCode(lore);
    }
}

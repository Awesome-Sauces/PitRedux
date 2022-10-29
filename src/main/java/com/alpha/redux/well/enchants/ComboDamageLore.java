package com.alpha.redux.well.enchants;

import com.alpha.redux.apis.Sounds;
import com.alpha.redux.entityHandlers.MysticHandler.Swords.ComboDamage;
import com.alpha.redux.entityHandlers.ReduxPlayer;
import com.alpha.redux.eventManagers.ReduxDamageEvent;
import com.alpha.redux.playerdata.economy;
import org.bukkit.scheduler.BukkitRunnable;

import static com.alpha.redux.apis.strikeLight.strikeLightningForPlayers;
import static com.alpha.redux.entityHandlers.MysticHandler.Swords.ComboDamage.hitCounter;
import static com.alpha.redux.events.boards.integerToRoman;

public class ComboDamageLore extends PitEnchant {

    @Override
    public void run(ReduxDamageEvent event, int level) {

        int count = 4;

        double damage = (double) ((10*level)+10)/100;

        if (ComboDamageCooldown(event.getAttacker()) && level > 1){count = 3;}

        addCounter(event);

        if(trigger(event, count)){
            event.addReduxDamage(event.getReduxDamage() * damage);
        }
    }

    private boolean ComboDamageCooldown(ReduxPlayer owner){
        if (owner.getComboDamageCD()){
            owner.setComboDamageCD();
            new BukkitRunnable() {
                @Override
                public void run() {
                    owner.setComboDamageCD();
                }
            }.runTaskLater(economy.getPlugin(), 5L);
            return true;
        }

        return false;
    }

    private void addCounter(ReduxDamageEvent event){
        if(ComboDamage.hitCounter.containsKey(event.getAttacker().getPlayerUUID())){
            ComboDamage.hitCounter.put(event.getAttacker().getPlayerUUID(), ComboDamage.hitCounter.get(event.getAttacker().getPlayerUUID()) + 1);
        }else{
            ComboDamage.hitCounter.put(event.getAttacker().getPlayerUUID(), 1);
        }
    }

    private boolean trigger(ReduxDamageEvent event, int count){
        if(ComboDamage.hitCounter.get(event.getAttacker().getPlayerUUID()) >= count){
            ComboDamage.hitCounter.put(event.getAttacker().getPlayerUUID(), 0);
            Sounds.COMBO_PROC.play(event.getAttacker().getPlayerObject());
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

        return "&9Combo: Damage" + tier;
    }

    @Override
    public String lore(int level) {

        String tier = "";
        if (level > 1){tier += " " + integerToRoman(level);}

        String multiplier = String.valueOf((10 * level) + 10);

        String hit = "";

        if (level > 1){
            hit = "third";
        }else {hit = "fourth";}

        String lore = "&9Combo: Damage" + tier + "\n" +
                "&7Every &e" + hit + " &7strike deals\n" + "&c+" + multiplier + "% &7damage" + "\n&7";

        return colorCode(lore);
    }
}


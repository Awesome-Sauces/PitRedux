package com.alpha.redux.UpgradesNpc.perks;

import com.alpha.redux.DeathHandler.ReduxDeathEvent;
import com.alpha.redux.entityHandlers.ReduxPlayer;
import com.alpha.redux.eventManagers.ReduxDamageEvent;
import com.alpha.redux.redux;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import static com.alpha.redux.apis.chatManager.rank.colorCode;

public class Gladiator extends PitPerk {

    public Gladiator(){
        this.setRefID("gladiator");
        this.setMaterial(Material.BONE);
        this.setName(colorCode("&aGladiator"));
        this.setLore(colorCode("&7Receive &9-3% &7damage per\n" +
                "&7nearby player.\n\n" +
                "&712 blocks range.\n" +
                "&7Minimum 3, max 10 players."));
        this.setCost(4000);
        this.setLevel(10);
    }

    @Override
    public PerkExecute getPerkExecute() {
        return new PerkExecute(){
            @Override
            public void run(ReduxDamageEvent event){
                ReduxPlayer player = event.getDefenders();

                if(player.getPerks().contains(redux.gladiator.getRefID())){
                    event.subtractReduxDamage(event.getReduxDamage() * getGladiator(player.getPlayerObject(), .03));
                }

            }

            @Override
            public void run(ReduxDeathEvent event){}
        };
    }

    public static double getGladiator(Player player, double multiplier){

        double power = 0;

        for(Entity entity : player.getNearbyEntities(7, 7, 7))
            if(entity instanceof Player) power += multiplier;

        return Math.min(power, multiplier * 10);
    }

}


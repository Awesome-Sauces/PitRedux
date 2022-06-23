package com.alpha.redux.entityHandlers.MysticHandler.Pants;

import com.alpha.redux.entityHandlers.MysticHandler.PantEnchant;
import com.alpha.redux.entityHandlers.ReduxPlayer;
import com.alpha.redux.eventManagers.ReduxDamageEvent;
import com.alpha.redux.playerdata.economy;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;


public class Regularity {


    public Regularity(ReduxDamageEvent event, ReduxPlayer player){


        PantEnchant regularity = new PantEnchant(event, player, "reg") {
            @Override
            public void OneAction() {
                triggerAttack(event, .10);
            }

            @Override
            public void TwoAction() {
                triggerAttack(event, .08);
            }

            @Override
            public void ThreeAction() {
                triggerAttack(event, .06);
            }
        };
        regularity.run();
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

}

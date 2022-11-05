package com.alpha.redux.entityHandlers.MysticHandler.Pants;

import com.alpha.redux.entityHandlers.MysticHandler.PantEnchant;
import com.alpha.redux.entityHandlers.ReduxPlayer;
import com.alpha.redux.eventManagers.ReduxDamageEvent;
import com.alpha.redux.playerdata.economy;
import com.alpha.redux.redux;
import com.alpha.redux.well.enchants.RegularityLore;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;


public class Regularity {

    public Regularity(ReduxDamageEvent event, ReduxPlayer player){


        PantEnchant regularity = new PantEnchant(event, player, "reg") {
            @Override
            public void OneAction() {
                redux.regularityLore.run(event, 1);
            }

            @Override
            public void TwoAction() {
                redux.regularityLore.run(event, 2);
            }

            @Override
            public void ThreeAction() {

                redux.regularityLore.run(event, 3);

            }
        };
        regularity.run();
    }

}

package com.alpha.redux.entityHandlers.MysticHandler.Pants;

import com.alpha.redux.entityHandlers.MysticHandler.PantEnchant;
import com.alpha.redux.entityHandlers.ReduxPlayer;
import com.alpha.redux.eventManagers.ReduxDamageEvent;
import com.alpha.redux.playerdata.economy;
import com.alpha.redux.well.enchants.RegularityLore;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;


public class Regularity {

    RegularityLore regularityL = new RegularityLore();


    public Regularity(ReduxDamageEvent event, ReduxPlayer player){


        PantEnchant regularity = new PantEnchant(event, player, "reg") {
            @Override
            public void OneAction() {
                regularityL.run(event, 3);
            }

            @Override
            public void TwoAction() {
                regularityL.run(event, 2);
            }

            @Override
            public void ThreeAction() {

                regularityL.run(event, 1);

            }
        };
        regularity.run();
    }

}

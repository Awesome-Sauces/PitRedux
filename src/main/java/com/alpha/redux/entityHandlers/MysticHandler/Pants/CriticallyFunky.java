package com.alpha.redux.entityHandlers.MysticHandler.Pants;

import com.alpha.redux.entityHandlers.MysticHandler.PantEnchant;
import com.alpha.redux.entityHandlers.ReduxPlayer;
import com.alpha.redux.eventManagers.ReduxDamageEvent;
import com.alpha.redux.well.enchants.CriticallyFunkyLore;

import org.bukkit.entity.Player;

public class CriticallyFunky {

    CriticallyFunkyLore criticalfunky = new CriticallyFunkyLore();

    public CriticallyFunky(ReduxDamageEvent event, ReduxPlayer player){
        PantEnchant criticallyFunky = new PantEnchant(event, player, "crit") {
            @Override
            public void OneAction() {
                criticalfunky.run(event, 1);
            }

            @Override
            public void TwoAction() {
                criticalfunky.run(event, 2);
            }

            @Override
            public void ThreeAction() {
                criticalfunky.run(event, 3);
            }
        };
        criticallyFunky.run();
    }

}

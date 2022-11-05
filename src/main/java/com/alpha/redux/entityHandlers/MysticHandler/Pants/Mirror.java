package com.alpha.redux.entityHandlers.MysticHandler.Pants;

import com.alpha.redux.entityHandlers.MysticHandler.PantEnchant;
import com.alpha.redux.entityHandlers.ReduxPlayer;
import com.alpha.redux.eventManagers.ReduxDamageEvent;
import com.alpha.redux.redux;
import com.alpha.redux.well.enchants.MirrorLore;

public class Mirror {
    public Mirror(ReduxDamageEvent event, ReduxPlayer player){
        PantEnchant mirror = new PantEnchant(event, player, "mirror") {
            @Override
            public void OneAction() {
                redux.mirrorLore.run(event, 1);
            }

            @Override
            public void TwoAction() {
                redux.mirrorLore.run(event, 1);
            }

            @Override
            public void ThreeAction() {
                redux.mirrorLore.run(event, 1);
            }
        };
        mirror.run();
    }
}

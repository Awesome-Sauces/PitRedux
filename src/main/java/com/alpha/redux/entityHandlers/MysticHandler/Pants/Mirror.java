package com.alpha.redux.entityHandlers.MysticHandler.Pants;

import com.alpha.redux.entityHandlers.MysticHandler.PantEnchant;
import com.alpha.redux.entityHandlers.ReduxPlayer;
import com.alpha.redux.eventManagers.ReduxDamageEvent;
import com.alpha.redux.well.enchants.MirrorLore;

public class Mirror {

    MirrorLore mirrors = new MirrorLore();

    public Mirror(ReduxDamageEvent event, ReduxPlayer player){
        PantEnchant mirror = new PantEnchant(event, player, "mirror") {
            @Override
            public void OneAction() {
                mirrors.run(event, 1);
            }

            @Override
            public void TwoAction() {
                mirrors.run(event, 1);
            }

            @Override
            public void ThreeAction() {
                mirrors.run(event, 1);
            }
        };
        mirror.run();
    }
}

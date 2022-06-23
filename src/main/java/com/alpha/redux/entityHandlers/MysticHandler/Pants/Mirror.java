package com.alpha.redux.entityHandlers.MysticHandler.Pants;

import com.alpha.redux.entityHandlers.MysticHandler.PantEnchant;
import com.alpha.redux.entityHandlers.ReduxPlayer;
import com.alpha.redux.eventManagers.ReduxDamageEvent;

public class Mirror {
    public Mirror(ReduxDamageEvent event, ReduxPlayer player){
        PantEnchant mirror = new PantEnchant(event, player, "mirror") {
            @Override
            public void OneAction() {
                this.event.subtractReduxTrueDamage(this.event.getReduxTrueDamage() * .75);
            }

            @Override
            public void TwoAction() {
                this.event.subtractReduxTrueDamage(this.event.getReduxTrueDamage() * .50);
            }

            @Override
            public void ThreeAction() {
                this.event.setReduxTrueDamage(0);
            }
        };
        mirror.run();
    }
}

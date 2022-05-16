package com.alpha.redux.entityHandlers.MysticHandler.Pants;

import com.alpha.redux.entityHandlers.MysticHandler.PantEnchant;
import com.alpha.redux.entityHandlers.ReduxPlayer;
import com.alpha.redux.eventManagers.ReduxDamageEvent;

public class Protection {
    public Protection(ReduxDamageEvent event, ReduxPlayer player){
        PantEnchant protection = new PantEnchant(event, player, "prot") {
            @Override
            public void OneAction() {
                this.event.subtractReduxDamage(this.event.getReduxDamage() *.04);
            }

            @Override
            public void TwoAction() {
                this.event.subtractReduxDamage(this.event.getReduxDamage() *.06);
            }

            @Override
            public void ThreeAction() {
                this.event.subtractReduxDamage(this.event.getReduxDamage() *.10);
            }
        };
        protection.run();
    }
}

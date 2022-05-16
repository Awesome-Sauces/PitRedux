package com.alpha.redux.entityHandlers.MysticHandler.Pants;

import com.alpha.redux.entityHandlers.MysticHandler.PantEnchant;
import com.alpha.redux.entityHandlers.ReduxPlayer;
import com.alpha.redux.eventManagers.ReduxDamageEvent;

public class FractionalReserve {
    public FractionalReserve(ReduxDamageEvent event, ReduxPlayer player){
        PantEnchant fractionalReserve = new PantEnchant(event, player, "frac") {
            @Override
            public void OneAction() {
                this.event.subtractReduxDamage(this.event.getReduxDamage() * Math.min((event.getDefenders().getPlayerGold() / 50000)*.01, .15));
            }

            @Override
            public void TwoAction() {
                this.event.subtractReduxDamage(this.event.getReduxDamage() * Math.min((event.getDefenders().getPlayerGold() / 50000)*.01, .21));
            }

            @Override
            public void ThreeAction() {
                this.event.subtractReduxDamage(this.event.getReduxDamage() * Math.min((event.getDefenders().getPlayerGold() / 50000)*.01, .30));
            }
        };
        fractionalReserve.run();
    }
}

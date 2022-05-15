package com.alpha.redux.entityHandlers.MysticHandler.Swords;

import com.alpha.redux.entityHandlers.MysticHandler.SwordEnchant;
import com.alpha.redux.eventManagers.ReduxDamageEvent;

import static com.alpha.redux.ItemEvents.sharkCalc.getSharkPlayers;

public class Sharp {
    public Sharp(ReduxDamageEvent event){
        SwordEnchant sharp = new SwordEnchant(event, "sharp") {
            @Override
            public void ThreeAction() {
                this.event.addReduxDamage(event.getReduxDamage() * .17);
            }
            @Override
            public void TwoAction() {
                this.event.addReduxDamage(event.getReduxDamage() * .10);

            }
            @Override
            public void OneAction() {
                this.event.addReduxDamage(event.getReduxDamage() * .07);
            }
        };

        sharp.run();
    }
}

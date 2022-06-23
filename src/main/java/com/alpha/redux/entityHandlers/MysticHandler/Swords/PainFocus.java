package com.alpha.redux.entityHandlers.MysticHandler.Swords;

import com.alpha.redux.entityHandlers.MysticHandler.SwordEnchant;
import com.alpha.redux.eventManagers.ReduxDamageEvent;

public class PainFocus {
    public PainFocus(ReduxDamageEvent event){
        SwordEnchant painfocus = new SwordEnchant(event, "pf") {
            @Override
            public void ThreeAction() {
                this.event.addReduxDamage(Math.min((event.getAttacker().getPlayerObject().getMaxHealth() - event.getAttacker().getPlayerObject().getHealth()) / 2, 2) * .08);
            }
            @Override
            public void TwoAction() {
                this.event.addReduxDamage(Math.min((event.getAttacker().getPlayerObject().getMaxHealth() - event.getAttacker().getPlayerObject().getHealth()) / 2, 2) * .05);

            }
            @Override
            public void OneAction() {
                this.event.addReduxDamage(Math.min((event.getAttacker().getPlayerObject().getMaxHealth() - event.getAttacker().getPlayerObject().getHealth()) / 2, 2) * .03);
            }
        };

        painfocus.run();
    }
}

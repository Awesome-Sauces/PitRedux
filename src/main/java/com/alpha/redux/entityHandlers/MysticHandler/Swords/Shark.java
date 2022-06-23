package com.alpha.redux.entityHandlers.MysticHandler.Swords;

import com.alpha.redux.entityHandlers.MysticHandler.SwordEnchant;
import com.alpha.redux.eventManagers.ReduxDamageEvent;

import static com.alpha.redux.ItemEvents.sharkCalc.getSharkPlayers;

public class Shark {
    public Shark(ReduxDamageEvent event){
        SwordEnchant shark = new SwordEnchant(event, "shark") {
            @Override
            public void ThreeAction() {
                this.event.addReduxDamage(this.event.getReduxDamage() * (Math.min(getSharkPlayers(this.event.getAttacker().getPlayerObject()), 18) * .07 ));
            }
            @Override
            public void TwoAction() {
                this.event.addReduxDamage(this.event.getReduxDamage() * (Math.min(getSharkPlayers(this.event.getAttacker().getPlayerObject()), 15) * .04 ));

            }
            @Override
            public void OneAction() {
                this.event.addReduxDamage(this.event.getReduxDamage() * (Math.min(getSharkPlayers(this.event.getAttacker().getPlayerObject()), 15) * .04 ));
            }
        };
        shark.run();
    }
}

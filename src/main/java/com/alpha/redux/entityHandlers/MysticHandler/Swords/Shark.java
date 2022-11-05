package com.alpha.redux.entityHandlers.MysticHandler.Swords;

import com.alpha.redux.entityHandlers.MysticHandler.SwordEnchant;
import com.alpha.redux.eventManagers.ReduxDamageEvent;
import com.alpha.redux.redux;
import com.alpha.redux.well.enchants.SharkLore;

import static com.alpha.redux.ItemEvents.sharkCalc.getSharkPlayers;

public class Shark {

    public Shark(ReduxDamageEvent event){
        SwordEnchant shark = new SwordEnchant(event, "shark") {
            @Override
            public void ThreeAction() {
                redux.sharkLore.run(event, 3);
            }
            @Override
            public void TwoAction() {
                redux.sharkLore.run(event, 2);
            }
            @Override
            public void OneAction() {
                redux.sharkLore.run(event, 1);
            }
        };
        shark.run();
    }
}

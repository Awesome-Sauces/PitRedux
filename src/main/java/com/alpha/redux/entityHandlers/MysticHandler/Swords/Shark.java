package com.alpha.redux.entityHandlers.MysticHandler.Swords;

import com.alpha.redux.entityHandlers.MysticHandler.SwordEnchant;
import com.alpha.redux.eventManagers.ReduxDamageEvent;
import com.alpha.redux.well.enchants.SharkLore;

import static com.alpha.redux.ItemEvents.sharkCalc.getSharkPlayers;

public class Shark {

    SharkLore sharkLore = new SharkLore();

    public Shark(ReduxDamageEvent event){
        SwordEnchant shark = new SwordEnchant(event, "shark") {
            @Override
            public void ThreeAction() {
                sharkLore.run(event, 3);
            }
            @Override
            public void TwoAction() {
                sharkLore.run(event, 2);
            }
            @Override
            public void OneAction() {
                sharkLore.run(event, 1);
            }
        };
        shark.run();
    }
}

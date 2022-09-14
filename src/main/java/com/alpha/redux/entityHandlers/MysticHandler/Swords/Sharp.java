package com.alpha.redux.entityHandlers.MysticHandler.Swords;

import com.alpha.redux.entityHandlers.MysticHandler.SwordEnchant;
import com.alpha.redux.eventManagers.ReduxDamageEvent;
import com.alpha.redux.well.enchants.SharpLore;

import static com.alpha.redux.ItemEvents.sharkCalc.getSharkPlayers;

public class Sharp {

    SharpLore sharpLore = new SharpLore();

    public Sharp(ReduxDamageEvent event){
        SwordEnchant sharp = new SwordEnchant(event, "sharp") {
            @Override
            public void ThreeAction() {
                sharpLore.run(event, 3);
            }
            @Override
            public void TwoAction() {
                sharpLore.run(event, 2);
            }
            @Override
            public void OneAction() {
                sharpLore.run(event, 1);
            }
        };

        sharp.run();
    }
}

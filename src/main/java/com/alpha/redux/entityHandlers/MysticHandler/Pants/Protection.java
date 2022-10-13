package com.alpha.redux.entityHandlers.MysticHandler.Pants;

import com.alpha.redux.entityHandlers.MysticHandler.PantEnchant;
import com.alpha.redux.entityHandlers.ReduxPlayer;
import com.alpha.redux.eventManagers.ReduxDamageEvent;
import com.alpha.redux.well.enchants.ProtectionLore;

public class Protection {

    ProtectionLore protectionL = new ProtectionLore();

    public Protection(ReduxDamageEvent event, ReduxPlayer player){
        PantEnchant protection = new PantEnchant(event, player, "prot") {
            @Override
            public void OneAction() {
                protectionL.run(event, 1);
            }

            @Override
            public void TwoAction() {
                protectionL.run(event, 2);
            }

            @Override
            public void ThreeAction() {
                protectionL.run(event, 3);
            }
        };
        protection.run();
    }
}

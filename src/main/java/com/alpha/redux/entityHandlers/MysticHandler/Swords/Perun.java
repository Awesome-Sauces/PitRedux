package com.alpha.redux.entityHandlers.MysticHandler.Swords;

import com.alpha.redux.entityHandlers.MysticHandler.SwordEnchant;
import com.alpha.redux.eventManagers.ReduxDamageEvent;
import com.alpha.redux.redux;
import com.alpha.redux.well.enchants.PerunLore;

import java.util.HashMap;

import static com.alpha.redux.apis.strikeLight.strikeLightningForPlayers;

public class Perun {

    public static HashMap<String, Integer> hitCounter = new HashMap<>();


    public Perun(ReduxDamageEvent event){
        SwordEnchant perun = new SwordEnchant(event, "perun") {
            @Override
            public void ThreeAction() {
                redux.perunLore.run(event, 3);
            }

            public void TwoAction() {
                redux.perunLore.run(event, 2);
            }

            public void OneAction() {
                redux.perunLore.run(event, 1);
            }
        };

        perun.run();
    }
}

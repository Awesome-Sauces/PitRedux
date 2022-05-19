package com.alpha.redux.entityHandlers.MysticHandler.Pants;

import com.alpha.redux.entityHandlers.MysticHandler.PantEnchant;
import com.alpha.redux.entityHandlers.ReduxPlayer;
import com.alpha.redux.eventManagers.ReduxDamageEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Regularity {

    private static List<UUID> hitCD = new ArrayList<UUID>();

    public Regularity(ReduxDamageEvent event, ReduxPlayer player){


        PantEnchant regularity = new PantEnchant(event, player, "reg") {
            @Override
            public void OneAction() {
                triggerAttack(event, .10);
            }

            @Override
            public void TwoAction() {
                triggerAttack(event, .08);
            }

            @Override
            public void ThreeAction() {
                triggerAttack(event, .06);
            }
        };
        regularity.run();
    }


    public void triggerAttack(ReduxDamageEvent event, double multiplier){
        if (!hitCD.contains(event.getDefenders().getPlayerObject().getUniqueId())){
            hitCD.add(event.getDefenders().getPlayerObject().getUniqueId());
            event.getDefenders().getPlayerObject().setNoDamageTicks(0);
            event.getDefenders().getPlayerObject().damage((event.getReduxDamage() / 100D) * multiplier, event.getAttacker().getPlayerObject());
            hitCD.remove(event.getDefenders().getPlayerObject().getUniqueId());
        }
    }

}

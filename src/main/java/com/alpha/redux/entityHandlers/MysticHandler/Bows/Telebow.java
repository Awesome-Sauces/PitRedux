package com.alpha.redux.entityHandlers.MysticHandler.Bows;

import com.alpha.redux.entityHandlers.MysticHandler.BowEnchant;
import com.alpha.redux.eventManagers.ReduxBowEvent;
import org.bukkit.Effect;

public class Telebow {
    public Telebow(ReduxBowEvent event){
        BowEnchant telebow = new BowEnchant(event, "tele") {
            @Override
            public void ThreeAction() {

                event.getAttacker().getPlayerObject().teleport(event.getDefender().getPlayerObject().getLocation());

            }
            @Override
            public void TwoAction() {

                event.getAttacker().getPlayerObject().teleport(event.getDefender().getPlayerObject().getLocation());

            }
            @Override
            public void OneAction() {

                event.getAttacker().getPlayerObject().teleport(event.getDefender().getPlayerObject().getLocation());

            }
        };

        telebow.run();
    }
}

package com.alpha.redux.entityHandlers.MysticHandler.Bows;

import com.alpha.redux.entityHandlers.MysticHandler.BowEnchant;
import com.alpha.redux.eventManagers.ReduxBowEvent;
import org.bukkit.Effect;
import org.bukkit.util.Vector;

public class Explosive {
    public Explosive(ReduxBowEvent event){
        BowEnchant explosive = new BowEnchant(event, "ex") {
            @Override
            public void ThreeAction() {

                event.getAttacker().getPlayerObject().getWorld().playEffect(event.getDefender().getPlayerObject().getLocation(), Effect.EXPLOSION_LARGE, 1);

            }
            @Override
            public void TwoAction() {

                event.getAttacker().getPlayerObject().getWorld().playEffect(event.getDefender().getPlayerObject().getLocation(), Effect.EXPLOSION_HUGE, 1);

            }
            @Override
            public void OneAction() {

                event.getAttacker().getPlayerObject().getWorld().playEffect(event.getDefender().getPlayerObject().getLocation(), Effect.EXPLOSION, 1);

            }
        };

        explosive.run();
    }
}

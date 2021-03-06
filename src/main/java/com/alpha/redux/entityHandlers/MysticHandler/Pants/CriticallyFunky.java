package com.alpha.redux.entityHandlers.MysticHandler.Pants;

import com.alpha.redux.entityHandlers.MysticHandler.PantEnchant;
import com.alpha.redux.entityHandlers.ReduxPlayer;
import com.alpha.redux.eventManagers.ReduxDamageEvent;
import org.bukkit.entity.Player;

public class CriticallyFunky {
    public CriticallyFunky(ReduxDamageEvent event, ReduxPlayer player){
        PantEnchant criticallyFunky = new PantEnchant(event, player, "crit") {
            @Override
            public void OneAction() {
                if(criticalHit(event.getAttacker().getPlayerObject())){
                    event.getDefenders().setPlayerIncrease(.07);
                    this.event.subtractReduxDamage(this.event.getReduxDamage() *.15);
                }
            }

            @Override
            public void TwoAction() {
                if(criticalHit(event.getAttacker().getPlayerObject())){
                    event.getDefenders().setPlayerIncrease(.14);
                    this.event.subtractReduxDamage(this.event.getReduxDamage() *.35);
                }
            }

            @Override
            public void ThreeAction() {
                if(criticalHit(event.getAttacker().getPlayerObject())){
                    event.getDefenders().setPlayerIncrease(.3);
                    this.event.subtractReduxDamage(this.event.getReduxDamage() *.5);
                }
            }
        };
        criticallyFunky.run();
    }

    private boolean criticalHit(Player player){
        return !player.isOnGround();
    }
}

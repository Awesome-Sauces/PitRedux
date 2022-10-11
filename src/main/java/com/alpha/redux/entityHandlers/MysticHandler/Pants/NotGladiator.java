package com.alpha.redux.entityHandlers.MysticHandler.Pants;

import com.alpha.redux.entityHandlers.MysticHandler.PantEnchant;
import com.alpha.redux.entityHandlers.ReduxPlayer;
import com.alpha.redux.eventManagers.ReduxDamageEvent;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class NotGladiator {
    public NotGladiator(ReduxDamageEvent event, ReduxPlayer player){
        PantEnchant notGladiator = new PantEnchant(event, player, "glad") {
            @Override
            public void OneAction() {
                this.event.subtractReduxDamage(this.event.getReduxDamage() * (getMultiplier(event.getDefenders().getPlayerObject(), .010)/10));
            }

            @Override
            public void TwoAction() {
                this.event.subtractReduxDamage(this.event.getReduxDamage() * (getMultiplier(event.getDefenders().getPlayerObject(), .015)/10));
            }

            @Override
            public void ThreeAction() {
                this.event.subtractReduxDamage(this.event.getReduxDamage() * (getMultiplier(event.getDefenders().getPlayerObject(), .02)/10));
            }
        };
        notGladiator.run();
    }


    private double getMultiplier(Player player, double multiplier){

        int power = 0;

        for(Entity entity : player.getNearbyEntities(15, 15, 15))
            if(entity instanceof Player) power += multiplier;

        return Math.min(power, multiplier * 10);
    }
}

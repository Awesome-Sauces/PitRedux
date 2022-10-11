package com.alpha.redux.entityHandlers.MysticHandler.Pants;

import com.alpha.redux.entityHandlers.MysticHandler.PantEnchant;
import com.alpha.redux.entityHandlers.ReduxPlayer;
import com.alpha.redux.eventManagers.ReduxDamageEvent;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class Solitude {

    ReduxPlayer player;

    public Solitude(ReduxDamageEvent event, ReduxPlayer player){
        this.player = player;
        PantEnchant solitude = new PantEnchant(event, player, "soli") {
            @Override
            public void OneAction() {
                if (solitaryCheck()) this.event.subtractReduxDamage(this.event.getReduxDamage() *.040);
            }

            @Override
            public void TwoAction() {
                if (solitaryCheck()) this.event.subtractReduxDamage(this.event.getReduxDamage() *.050);
            }

            @Override
            public void ThreeAction() {
                if (solitaryCheck()) this.event.subtractReduxDamage(this.event.getReduxDamage() *.060);
            }
        };

        solitude.run();




    }


    private boolean solitaryCheck(){
        Player location = player.getPlayerObject();
        int playerAmount = 0;

        for(Entity entity : location.getNearbyEntities(7, 7, 7))
            if(entity instanceof Player) playerAmount += 1;

        return playerAmount <= 2;
    }
}

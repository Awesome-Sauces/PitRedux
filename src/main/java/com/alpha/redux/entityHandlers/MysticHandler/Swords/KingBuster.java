package com.alpha.redux.entityHandlers.MysticHandler.Swords;

import com.alpha.redux.apis.Sounds;
import com.alpha.redux.entityHandlers.MysticHandler.SwordEnchant;
import com.alpha.redux.eventManagers.ReduxDamageEvent;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Material;

import static com.alpha.redux.ItemEvents.sharkCalc.getSharkPlayers;

public class KingBuster {
    public KingBuster(ReduxDamageEvent event){
        SwordEnchant kingBuster = new SwordEnchant(event, "king") {
            @Override
            public void ThreeAction() {
                if(triggerExecutioner(event)){
                    this.event.addReduxDamage(this.event.getReduxDamage()  * .20);
                }
            }
            @Override
            public void TwoAction() {
                if(triggerExecutioner(event)){
                    this.event.addReduxDamage(this.event.getReduxDamage()  * .13);
                }

            }
            @Override
            public void OneAction() {
                if(triggerExecutioner(event)){
                    this.event.addReduxDamage(this.event.getReduxDamage()  * .07);
                }
            }
        };
        kingBuster.run();
    }


    private boolean triggerExecutioner(ReduxDamageEvent event){
        double damage = event.getReduxTrueDamage() + event.getReduxTrueDamage();
        return event.getDefenders().getPlayerObject().getHealth() - damage <= 10;
    }
}

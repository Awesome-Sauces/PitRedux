package com.alpha.redux.entityHandlers.MysticHandler.Swords;

import com.alpha.redux.entityHandlers.MysticHandler.SwordEnchant;
import com.alpha.redux.eventManagers.ReduxDamageEvent;
import org.bukkit.Bukkit;

public class Lifesteal {
    public Lifesteal(ReduxDamageEvent event){
        SwordEnchant lifesteal = new SwordEnchant(event, "ls") {
            @Override
            public void ThreeAction() {
                this.event.getAttacker().getPlayerObject().setHealth(Math.min(this.event.getAttacker().getPlayerObject().getHealth()
                                + Math.min(((this.event.getAttacker().getPlayerObject().getHealth()/2) * .13), 3),
                        this.event.getAttacker().getPlayerObject().getMaxHealth()));
            }
            @Override
            public void TwoAction() {
                this.event.getAttacker().getPlayerObject().setHealth(Math.min(this.event.getAttacker().getPlayerObject().getHealth()
                                + Math.min(((this.event.getAttacker().getPlayerObject().getHealth()/2) * .08), 2),
                        this.event.getAttacker().getPlayerObject().getMaxHealth()));
            }
            @Override
            public void OneAction() {
                this.event.getAttacker().getPlayerObject().setHealth(Math.min(this.event.getAttacker().getPlayerObject().getHealth()
                                + Math.min(((this.event.getAttacker().getPlayerObject().getHealth()/2) * .04), 1.5),
                        this.event.getAttacker().getPlayerObject().getMaxHealth()));
            }
        };

        lifesteal.run();


    }
}

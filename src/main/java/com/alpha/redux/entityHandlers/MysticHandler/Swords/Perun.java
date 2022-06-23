package com.alpha.redux.entityHandlers.MysticHandler.Swords;

import com.alpha.redux.entityHandlers.MysticHandler.SwordEnchant;
import com.alpha.redux.eventManagers.ReduxDamageEvent;

import java.util.HashMap;

import static com.alpha.redux.apis.strikeLight.strikeLightningForPlayers;

public class Perun {

    public static HashMap<String, Integer> hitCounter = new HashMap<>();

    public Perun(ReduxDamageEvent event){
        SwordEnchant perun = new SwordEnchant(event, "perun") {
            @Override
            public void ThreeAction() {
                Perun.this.addCounter(event);

                if(trigger(event, 4)){
                    this.event.addReduxTrueDamage(6);
                }

            }

            public void TwoAction() {
                Perun.this.addCounter(event);

                if(trigger(event, 4)){
                    this.event.addReduxTrueDamage(4);
                }
                
            }

            public void OneAction() {
                Perun.this.addCounter(event);

                if(trigger(event, 5)){
                    this.event.addReduxTrueDamage(2);
                }
                
            }
        };

        perun.run();
    }

    private void addCounter(ReduxDamageEvent event){
        if(hitCounter.containsKey(event.getAttacker().getPlayerUUID())){
            hitCounter.put(event.getAttacker().getPlayerUUID(), hitCounter.get(event.getAttacker().getPlayerUUID()) + 1);
        }else{
            hitCounter.put(event.getAttacker().getPlayerUUID(), 1);
        }
    }

    private boolean trigger(ReduxDamageEvent event, int count){
        if(hitCounter.get(event.getAttacker().getPlayerUUID()) >= count){
            hitCounter.put(event.getAttacker().getPlayerUUID(), 0);
            strikeLightningForPlayers(event.getDefenders().getPlayerObject().getLocation(), event.getAttacker().getPlayerObject(), event.getDefenders().getPlayerObject());
            return true;
        }else return false;
    }
}

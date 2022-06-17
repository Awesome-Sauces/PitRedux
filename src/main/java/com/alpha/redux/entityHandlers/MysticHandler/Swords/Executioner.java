package com.alpha.redux.entityHandlers.MysticHandler.Swords;

import com.alpha.redux.apis.Sounds;
import com.alpha.redux.entityHandlers.MysticHandler.SwordEnchant;
import com.alpha.redux.eventManagers.ReduxDamageEvent;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Material;

import static com.alpha.redux.DeathHandler.killHandler.isNPC;
import static com.alpha.redux.ItemEvents.sharkCalc.getSharkPlayers;

public class Executioner {
    public Executioner(ReduxDamageEvent event){
        SwordEnchant executioner = new SwordEnchant(event, "exe") {
            @Override
            public void ThreeAction() {
                triggerExecutioner(event, 7);
            }
            @Override
            public void TwoAction() {
                triggerExecutioner(event, 5);

            }
            @Override
            public void OneAction() {
                triggerExecutioner(event, 3);
            }
        };
        executioner.run();
    }


    private void triggerExecutioner(ReduxDamageEvent event, double tier){

        if(isNPC(event.getDefenders().getPlayerObject())) tier+=5;

        double damage = event.getReduxTrueDamage() + event.getReduxTrueDamage();


        if(event.getDefenders().getPlayerObject().getHealth() - damage <= tier){
            Sounds.EXE.play(event.getAttacker().getPlayerObject());
            Bukkit.getWorld("world").playEffect(event.getDefenders().getPlayerObject().getLocation(), Effect.STEP_SOUND, Material.REDSTONE_BLOCK);
            event.getDefenders().killPlayer(event.getAttacker().getPlayerObject());
            event.getBukkitEvent().setCancelled(true);
        }
    }

}

package com.alpha.redux.UpgradesNpc.perks;

import com.alpha.redux.DeathHandler.ReduxDeathEvent;
import com.alpha.redux.eventManagers.ReduxDamageEvent;
import com.alpha.redux.redux;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import static com.alpha.redux.DeathHandler.killHandler.isNPC;

public class PerkHandler implements Listener {

    @EventHandler
    public void PlayerDamageEvent(ReduxDamageEvent event){
        if(isNPC(event.getAttacker().getPlayerObject())) return;

        redux.vampire.getPerkExecute().run(event);
        redux.strengthChaining.getPerkExecute().run(event);
        redux.gladiator.getPerkExecute().run(event);
    }

    @EventHandler
    public void PlayerDeathEvent(ReduxDeathEvent event){
        if(isNPC(event.getAttacker().getPlayerObject())) return;

        redux.strengthChaining.getPerkExecute().run(event);
        redux.goldenHeads.getPerkExecute().run(event);
        redux.dirty.getPerkExecute().run(event);
        redux.streaker.getPerkExecute().run(event);

    }

}

package com.alpha.redux.entityHandlers.MysticHandler.Swords;

import com.alpha.redux.apis.Sounds;
import com.alpha.redux.entityHandlers.MysticHandler.SwordEnchant;
import com.alpha.redux.eventManagers.ReduxDamageEvent;
import com.alpha.redux.redux;
import com.alpha.redux.well.enchants.ExecutionerLore;
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
                redux.executionerLore.run(event, 3);
            }
            @Override
            public void TwoAction() {
                redux.executionerLore.run(event, 2);
            }
            @Override
            public void OneAction() {
                redux.executionerLore.run(event, 1);
            }
        };
        executioner.run();
    }

}

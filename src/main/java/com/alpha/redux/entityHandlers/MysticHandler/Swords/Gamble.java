package com.alpha.redux.entityHandlers.MysticHandler.Swords;

import com.alpha.redux.apis.Sounds;
import com.alpha.redux.entityHandlers.MysticHandler.SwordEnchant;
import com.alpha.redux.entityHandlers.TrueDamage.TrueDamageHandler;
import com.alpha.redux.eventManagers.ReduxDamageEvent;
import com.alpha.redux.items.enchants;
import com.alpha.redux.well.enchants.GambleLore;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

import java.util.Random;

import static com.alpha.redux.entityHandlers.ReduxPlayerHandler.playerExists;

public class Gamble {
    public Gamble(ReduxDamageEvent event){

        GambleLore gambleLore = new GambleLore();

        SwordEnchant gamble = new SwordEnchant(event, "gamb") {
            @Override
            public void ThreeAction() {
                gambleLore.run(event,3);
            }
            @Override
            public void TwoAction() {
                gambleLore.run(event,2);
            }
            @Override
            public void OneAction() {
                gambleLore.run(event,1);
            }
        };

        gamble.run();
    }

}

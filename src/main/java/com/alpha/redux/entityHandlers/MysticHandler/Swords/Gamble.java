package com.alpha.redux.entityHandlers.MysticHandler.Swords;

import com.alpha.redux.apis.Sounds;
import com.alpha.redux.entityHandlers.MysticHandler.SwordEnchant;
import com.alpha.redux.entityHandlers.TrueDamage.TrueDamageHandler;
import com.alpha.redux.eventManagers.ReduxDamageEvent;
import com.alpha.redux.items.enchants;
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


        SwordEnchant gamble = new SwordEnchant(event, "gamb") {
            @Override
            public void ThreeAction() {
                Random rand = new Random(); //instance of random class
                int upperbound = 2;
                int int_random = rand.nextInt(upperbound);
                switch (int_random){
                    case 0:
                        Sounds.GAMBLE_YES.play(this.event.getAttacker().getPlayerObject());
                        this.event.addReduxTrueDamage(6);
                        break;
                    case 1:
                        Sounds.GAMBLE_NO.play(this.event.getAttacker().getPlayerObject());
                        gambleCalc(this.event.getAttacker().getPlayerObject(), 6);
                        break;
                }

            }
            @Override
            public void TwoAction() {
                Random rand = new Random(); //instance of random class
                int upperbound = 2;
                int int_random = rand.nextInt(upperbound);
                switch (int_random){
                    case 0:
                        Sounds.GAMBLE_YES.play(this.event.getAttacker().getPlayerObject());
                        this.event.addReduxTrueDamage(4);
                        break;
                    case 1:
                        Sounds.GAMBLE_NO.play(this.event.getAttacker().getPlayerObject());
                        gambleCalc(this.event.getAttacker().getPlayerObject(), 4);
                        break;
                }
            }
            @Override
            public void OneAction() {
                Random rand = new Random(); //instance of random class
                int upperbound = 2;
                int int_random = rand.nextInt(upperbound);
                switch (int_random){
                    case 0:
                        Sounds.GAMBLE_YES.play(this.event.getAttacker().getPlayerObject());
                        this.event.addReduxTrueDamage(2);
                        break;
                    case 1:
                        Sounds.GAMBLE_NO.play(this.event.getAttacker().getPlayerObject());
                        gambleCalc(this.event.getAttacker().getPlayerObject(), 2);
                        break;
                }
            }
        };

        gamble.run();
    }

    private void gambleCalc(Player player, double trueAmount){
        new TrueDamageHandler(playerExists(player), playerExists(player), trueAmount, 0).run();
    }

}

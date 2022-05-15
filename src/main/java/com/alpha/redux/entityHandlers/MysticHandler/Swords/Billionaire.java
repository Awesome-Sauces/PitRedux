package com.alpha.redux.entityHandlers.MysticHandler.Swords;

import com.alpha.redux.apis.Sounds;
import com.alpha.redux.entityHandlers.MysticHandler.SwordEnchant;
import com.alpha.redux.eventManagers.ReduxDamageEvent;
import com.alpha.redux.events.boards;

import static com.alpha.redux.playerdata.economy.getEconomy;
import static com.alpha.redux.playerdata.economy.removeEconomy;

public class Billionaire {

    public Billionaire(ReduxDamageEvent event){
        SwordEnchant billionaire = new SwordEnchant(event, "bill") {
            @Override
            public void ThreeAction() {
                if (event.getAttacker().getPlayerGold() >= 350) {
                    event.getAttacker().setPlayerGold((int) (event.getAttacker().getPlayerGold() - 350));
                    event.getAttacker().refreshScoreBoard();
                    Sounds.BILLIONAIRE.play(event.getAttacker().getPlayerObject());
                    this.event.addReduxDamage(event.getReduxDamage() * 1.5);
                }
            }
            @Override
            public void TwoAction() {
                if (event.getAttacker().getPlayerGold() >= 200) {
                    event.getAttacker().setPlayerGold((int) (event.getAttacker().getPlayerGold() - 200));
                    event.getAttacker().refreshScoreBoard();
                    Sounds.BILLIONAIRE.play(event.getAttacker().getPlayerObject());
                    this.event.addReduxDamage(event.getReduxDamage() * 1.25);
                }
            }
            @Override
            public void OneAction() {
                if (event.getAttacker().getPlayerGold() >= 100) {
                    event.getAttacker().setPlayerGold((int) (event.getAttacker().getPlayerGold() - 100));
                    event.getAttacker().refreshScoreBoard();
                    Sounds.BILLIONAIRE.play(event.getAttacker().getPlayerObject());
                    this.event.addReduxDamage(event.getReduxDamage() * 1);
                }
            }
        };

        billionaire.run();
    }

}

package com.alpha.redux.entityHandlers.MysticHandler;

import com.alpha.redux.entityHandlers.MysticHandler.Pants.*;
import com.alpha.redux.entityHandlers.MysticHandler.Swords.*;
import com.alpha.redux.entityHandlers.ReduxPlayer;
import com.alpha.redux.eventManagers.ReduxDamageEvent;
import com.alpha.redux.well.EnchantingMechanics;
import com.alpha.redux.well.MysticType;

import java.util.ArrayList;
import java.util.List;

public class EnchantRuntime {

    ReduxPlayer attacker;

    String mysticType;

    List<String> mysticPants = new ArrayList<>();
    List<String> mysticSword = new ArrayList<>();
    ReduxDamageEvent event;

    public EnchantRuntime(ReduxPlayer attacker, ReduxPlayer defender, ReduxDamageEvent event, String mysticType){
        this.attacker = attacker;
        if(this.attacker.getPantEnchants() != null) mysticPants = attacker.getPantEnchants();
        if(this.attacker.getSwordEnchants() != null) mysticSword = attacker.getSwordEnchants();
        this.event = event;
        this.mysticType = mysticType;
    }

    public boolean run(){

        if(MysticType.valueOf(mysticType).equals(MysticType.SWORD)){
            new Billionaire(event);
            new DiamondStomp(event);
            new Executioner(event);
            new Gamble(event);
            new KingBuster(event);
            new Lifesteal(event);
            new PainFocus(event);
            new Perun(event);
            new Shark(event);
            new Sharp(event);
        }else if(MysticType.valueOf(mysticType).equals(MysticType.PANT)){
            new CriticallyFunky(event, event.getDefenders());
            new EscapePod(event, event.getDefenders());
            new FractionalReserve(event, event.getDefenders());
            new Mirror(event, event.getDefenders());
            new NotGladiator(event, event.getDefenders());
            new Peroxide(event, event.getDefenders());
            new Protection(event, event.getDefenders());
            new RetroGravityMicrocosm(event, event.getDefenders());
            new Solitude(event, event.getDefenders());
            new Regularity(event, attacker);


        }


        return false;
    }

}

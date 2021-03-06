package com.alpha.redux.entityHandlers.MysticHandler;

import com.alpha.redux.entityHandlers.MysticHandler.Bows.*;
import com.alpha.redux.entityHandlers.MysticHandler.Pants.*;
import com.alpha.redux.entityHandlers.MysticHandler.Swords.*;
import com.alpha.redux.entityHandlers.ReduxPlayer;
import com.alpha.redux.eventManagers.ReduxBowEvent;
import com.alpha.redux.eventManagers.ReduxDamageEvent;
import com.alpha.redux.well.EnchantingMechanics;
import com.alpha.redux.well.MysticType;
import org.bukkit.Bukkit;
import org.bukkit.event.entity.EntityShootBowEvent;

import java.util.ArrayList;
import java.util.List;

public class EnchantRuntime {


    String mysticType;

    ReduxDamageEvent event;
    ReduxBowEvent bowEvent;
    EntityShootBowEvent shootEvent;

    public EnchantRuntime(ReduxDamageEvent event, String mysticType){
        this.event = event;
        this.mysticType = mysticType;
    }

    public EnchantRuntime(ReduxBowEvent event, String mysticType){
        this.bowEvent = event;
        this.mysticType = mysticType;
    }

    public EnchantRuntime(EntityShootBowEvent event){
        this.shootEvent = event;
    }

    public boolean bowRun(){

        new SprintDrain(this.bowEvent);
        new Explosive(this.bowEvent);
        new FasterThanTheirShadow(this.bowEvent);
        new PullBow(this.bowEvent);
        new Telebow(this.bowEvent);

        return true;
    }

    public boolean bowShoot(){
        new MegaLongBow(this.shootEvent);
        new Volley(this.shootEvent);

        return true;
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
            return false;
        }

        if(MysticType.valueOf(mysticType).equals(MysticType.PANT)){

            new CriticallyFunky(event, event.getDefenders());
            new EscapePod(event, event.getDefenders());
            new FractionalReserve(event, event.getDefenders());
            new Mirror(event, event.getDefenders());
            new NotGladiator(event, event.getDefenders());
            new Peroxide(event, event.getDefenders());
            new Protection(event, event.getDefenders());
            new Solitude(event, event.getDefenders());
            new RetroGravityMicrocosm(event, event.getDefenders());
            new Regularity(event, event.getAttacker());

            return false;

        }


        return false;
    }

}

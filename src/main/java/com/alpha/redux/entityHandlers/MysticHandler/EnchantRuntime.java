package com.alpha.redux.entityHandlers.MysticHandler;

import com.alpha.redux.entityHandlers.MysticHandler.Bows.*;
import com.alpha.redux.entityHandlers.MysticHandler.Pants.*;
import com.alpha.redux.entityHandlers.MysticHandler.Swords.*;
import com.alpha.redux.entityHandlers.ReduxPlayer;
import com.alpha.redux.eventManagers.ReduxBowEvent;
import com.alpha.redux.eventManagers.ReduxDamageEvent;
import com.alpha.redux.well.EnchantingMechanics;
import com.alpha.redux.well.MysticType;
import com.alpha.redux.well.enchants.DiamondAllergyLore;
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

        new SprintDrain(bowEvent);
        new Explosive(bowEvent);
        new FasterThanTheirShadow(bowEvent);
        new PullBow(bowEvent);
        new Telebow(bowEvent);

        return true;
    }

    public boolean bowShoot(){

        new MegaLongBow(shootEvent);
        new Volley(shootEvent);

        return true;
    }

    public boolean run(){

        if(MysticType.valueOf(mysticType).equals(MysticType.SWORD)){
            new Billionaire(event);
            new DiamondStomp(event);
            new Executioner(event);
            new Gamble(event);
            new KingBuster(event);
            new PainFocus(event);
            new Perun(event);
            new Shark(event);
            new Sharp(event);
            new Lifesteal(event);
            new Mirror(event, event.getDefenders());
            return false;
        }

        if(MysticType.valueOf(mysticType).equals(MysticType.PANT)){

            new BooBoo(event, event.getDefenders());
            new CriticallyFunky(event, event.getDefenders());
            new DiamondAllergy(event, event.getDefenders());
            new EscapePod(event, event.getDefenders());
            new FractionalReserve(event, event.getDefenders());
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

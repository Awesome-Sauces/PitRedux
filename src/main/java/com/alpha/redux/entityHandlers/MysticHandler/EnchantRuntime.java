package com.alpha.redux.entityHandlers.MysticHandler;

import com.alpha.redux.entityHandlers.MysticHandler.Bows.*;
import com.alpha.redux.entityHandlers.MysticHandler.Pants.*;
import com.alpha.redux.entityHandlers.MysticHandler.Swords.*;
import com.alpha.redux.entityHandlers.ReduxPlayer;
import com.alpha.redux.eventManagers.ReduxBowEvent;
import com.alpha.redux.eventManagers.ReduxDamageEvent;
import com.alpha.redux.well.EnchantingMechanics;
import com.alpha.redux.well.MysticType;
import com.alpha.redux.well.enchants.CricketLore;
import com.alpha.redux.well.enchants.DiamondAllergyLore;
import org.bukkit.Bukkit;
import org.bukkit.Material;
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
        new FasterThanTheirShadow(bowEvent);
        new PullBow(bowEvent);
        return true;
    }

    public boolean bowShoot(){

        new MegaLongBow(shootEvent);
        new Volley(shootEvent);
        new Telebow(shootEvent);

        return true;
    }

    public boolean run(){

        if(MysticType.valueOf(mysticType).equals(MysticType.SWORD)){
            new FancyRaider(event);
            new Punisher(event);
            new PitPocket(event);
            new Grasshopper(event);
            new GoldBoosted(event);
            new DiamondStomp(event);
            new Executioner(event);
            new Gamble(event);
            new KingBuster(event);
            new PainFocus(event);
            new Perun(event);
            new ComboDamage(event);
            new Shark(event);
            new Sharp(event);
            new Billionaire(event);
            new Lifesteal(event);
            new Mirror(event, event.getDefenders());
            return false;
        }

        if(MysticType.valueOf(mysticType).equals(MysticType.PANT)){

            new SelfCheckout(event, event.getDefenders());
            new Prick(event, event.getDefenders());
            new Billy(event, event.getDefenders());
            new Cricket(event, event.getDefenders());

            new BooBoo(event, event.getDefenders());
            new Cricket(event, event.getDefenders());
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

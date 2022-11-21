package com.alpha.redux.UpgradesNpc.perks;

import com.alpha.redux.DeathHandler.ReduxDeathEvent;
import com.alpha.redux.entityHandlers.ReduxPlayer;
import com.alpha.redux.eventManagers.ReduxDamageEvent;
import com.alpha.redux.redux;
import org.bukkit.Material;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import static com.alpha.redux.apis.chatManager.rank.colorCode;

public class Streaker extends PitPerk {

    public Streaker(){
        this.setRefID("streaker");
        this.setMaterial(Material.WHEAT);
        this.setName(colorCode("Streaker"));
        this.setLore(colorCode("&7Triple streak kill &bbase XP &7bonus."));
        this.setCost(8000);
        this.setLevel(50);
    }

    @Override
    public PerkExecute getPerkExecute() {
        return new PerkExecute(){
            @Override
            public void run(ReduxDamageEvent event){

            }

            @Override
            public void run(ReduxDeathEvent event){
                ReduxPlayer player = event.getAttacker();

                if(player.getPerks().contains(redux.streaker.getRefID())){
                    event.addXp(event.getXp()*3);
                }

            }
        };
    }


}

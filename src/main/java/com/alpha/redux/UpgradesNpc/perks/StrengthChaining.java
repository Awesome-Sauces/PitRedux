package com.alpha.redux.UpgradesNpc.perks;

import com.alpha.redux.DeathHandler.ReduxDeathEvent;
import com.alpha.redux.entityHandlers.ReduxPlayer;
import com.alpha.redux.eventManagers.ReduxDamageEvent;
import com.alpha.redux.redux;
import org.bukkit.Material;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import static com.alpha.redux.apis.chatManager.rank.colorCode;

public class StrengthChaining extends PitPerk {

    public StrengthChaining(){
        this.setRefID("strength");
        this.setMaterial(Material.REDSTONE);
        this.setName(colorCode("Strength-Chaining"));
        this.setLore(colorCode("&c+8% damage &7for 7s stacking\n" +
                "&7on kill."));
        this.setCost(4000);
        this.setLevel(25);
    }

    @Override
    public PerkExecute getPerkExecute() {
        return new PerkExecute(){
            @Override
            public void run(ReduxDamageEvent event){
                ReduxPlayer player = event.getAttacker();


                if(player.getPerks().contains(redux.strengthChaining.getRefID())){
                    if(player.getStrength()<=0) return;
                    event.addReduxDamage(event.getReduxDamage() * player.getStrength());
                }



            }

            @Override
            public void run(ReduxDeathEvent event){
                ReduxPlayer player = event.getAttacker();

                if(player.getPerks().contains(redux.strengthChaining.getRefID())){
                    player.strengthTick();
                }

            }
        };
    }


}


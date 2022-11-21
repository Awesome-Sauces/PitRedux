package com.alpha.redux.UpgradesNpc.perks;

import com.alpha.redux.DeathHandler.ReduxDeathEvent;
import com.alpha.redux.entityHandlers.ReduxPlayer;
import com.alpha.redux.eventManagers.ReduxDamageEvent;
import com.alpha.redux.redux;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import static com.alpha.redux.DeathHandler.killHandler.isNPC;
import static com.alpha.redux.apis.chatManager.rank.colorCode;
import static com.alpha.redux.events.nonPermItems.ClearAndCheck;
import static com.alpha.redux.events.nonPermItems.ClearRegular;
import static com.alpha.redux.playerdata.streaks.*;

public class AssistantStreaker extends PitPerk {

    public AssistantStreaker(){
        this.setRefID("assistant");
        this.setMaterial(Material.SPRUCE_FENCE);
        this.setName(colorCode("Assistant Streaker"));
        this.setLore(colorCode("&7Assists count their\n" +
                "&aparticipation &7towards\n" +
                "&7killstreaks.\n\n" +
                "&7Earn &6+2g &7and &b+15% XP\n" +
                "&7from kills and assists.\n\n" +
                "&7Gain &e+1 &7streak every &c4th\n" +
                "&ckill&7."));
        this.setCost(8000);
        this.setLevel(25);
    }

    @Override
    public PerkExecute getPerkExecute() {
        return new PerkExecute(){
            @Override
            public void run(ReduxDamageEvent event){}

            @Override
            public void run(ReduxDeathEvent event){
                ReduxPlayer player = event.getAttacker();
                ReduxPlayer defender = event.getDefender();

                hasStreak(defender.getPlayerUUID());

                if(player.getPerks().contains(redux.assistantStreaker.getRefID())){
                    event.addGold(2);
                    event.addXp(event.getXp()*.15);
                }

                if(player.getPerks().contains(redux.assistantStreaker.getRefID())) if(player.tickAssistantStreaker()>=4){
                    player.resetAssistantStreaker();
                    hasStreak(player.getPlayerUUID());
                    addStreak(player.getPlayerUUID(),1);
                }
            }
        };
    }


}


package com.alpha.redux.UpgradesNpc.perks;

import com.alpha.redux.DeathHandler.ReduxDeathEvent;
import com.alpha.redux.UpgradesNpc.perks.items.GoldenHeadItem;
import com.alpha.redux.entityHandlers.ReduxPlayer;
import com.alpha.redux.eventManagers.ReduxDamageEvent;
import com.alpha.redux.items.enchants;
import com.alpha.redux.redux;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import static com.alpha.redux.apis.chatManager.rank.colorCode;

public class GoldenHeads extends PitPerk {

    public GoldenHeads(){
        this.setRefID("goldenhead");
        this.setName(colorCode("&aGolden Heads"));
        this.setLore(colorCode("&7Golden apples you earn turn into\n" +
                "&6Golden Heads&7."));
        this.setCost(1000);
        this.setLevel(5);
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

                if(player.getPerks().contains(redux.goldenHeads.getRefID())){
                    if(player.getPlayerObject().getInventory().contains(enchants.goldenhead.getType(), 2)) return;
                    player.getPlayerObject().getInventory().addItem(enchants.goldenhead);
                }

            }
        };
    }


}


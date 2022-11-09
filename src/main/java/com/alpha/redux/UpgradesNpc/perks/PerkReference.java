package com.alpha.redux.UpgradesNpc.perks;

import com.alpha.redux.redux;
import org.bukkit.ChatColor;

public class PerkReference {
    public static String getPerkLore(String refID){

        if(refID.equals(redux.goldenHeads.getRefID())){
            return redux.goldenHeads.getLore();
        }else if(refID.equals(redux.vampire.getRefID())){
            return redux.vampire.getLore();
        }else if(refID.equals(redux.streaker.getRefID())){
            return redux.streaker.getLore();
        }else if(refID.equals(redux.strengthChaining.getRefID())){
            return redux.strengthChaining.getLore();
        }else if(refID.equals(redux.gladiator.getRefID())){
            return redux.gladiator.getLore();
        }else if(refID.equals(redux.dirty.getRefID())){
            return redux.dirty.getLore();
        }else{
            return ChatColor.GRAY + "Select a perk to fill this slot.";
        }

        /*
        else if(refID.equals(redux..getRefID())){
            return redux..getLore();
        }
         */
    }
}
